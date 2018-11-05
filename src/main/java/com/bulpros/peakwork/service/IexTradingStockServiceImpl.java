package com.bulpros.peakwork.service;

import com.google.cloud.Timestamp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulpros.peakwork.entity.Stock;
import com.bulpros.peakwork.repository.StockRepository;

import pl.zankowski.iextrading4j.api.stocks.BatchStocks;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.BatchMarketStocksRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.BatchStocksType;

/**
 * The Class IexTradingStockServiceImpl.
 */
@Service
public class IexTradingStockServiceImpl implements IexTradingStockService {

    /** The stock repository. */
    @Autowired
    StockRepository stockRepository;

    /** 
     * @see com.bulpros.peakwork.service.IexTradingStockService#fetchStocks(java.lang.String[])
     */
    @Override
    public Supplier<Map<String, BatchStocks>> fetchStocks(String[] symbols) {
        final IEXTradingClient iexTradingClient = IEXTradingClient.create();

        return () -> iexTradingClient.executeRequest(
                new BatchMarketStocksRequestBuilder()
                    .withSymbols(Arrays.asList(symbols))
                    .addType(BatchStocksType.PRICE)
                    .addType(BatchStocksType.LOGO)
                    .addType(BatchStocksType.KEY_STATS)
                    .build());
    }

    /**
     * @see com.bulpros.peakwork.service.IexTradingStockService#retrieveStocks(java.lang.String[], java.time.LocalDateTime, java.time.LocalDateTime)
     */
    @Override
    public List<Stock> retrieveStocks(String[] symbols, LocalDateTime fromDate, LocalDateTime toDate) {
        List<Stock> result = new ArrayList<Stock>();
        Timestamp fromTimestamp = Timestamp.of(Date.from(fromDate.toInstant(ZoneOffset.UTC)));
        Timestamp toTimestamp = Timestamp.of(Date.from(toDate.toInstant(ZoneOffset.UTC)));
        for (String symbol : symbols) {
            result.addAll(stockRepository
                    .findAllBySymbolAndCreatedGreaterThanEqualAndCreatedLessThanEqualOrderByCreated(
                            symbol.toUpperCase(), fromTimestamp, toTimestamp));
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.bulpros.peakwork.service.IexTradingStockService#storeStocks(java.util.function.Supplier)
     */
    @Override
    public void storeStocks(Supplier<Map<String, BatchStocks>> stocks) {
        stocks.get().forEach((k, v) -> {
            Stock stock = new Stock();
            stock.setSymbol(k);
            stock.setPrice(v.getPrice().toEngineeringString());
            stock.setLogo(v.getLogo().getUrl());
            stock.setCompanyName(v.getKeyStats().getCompanyName());
            stock.setCreated(LocalDateTime.now());
            stockRepository.save(stock);
        });
    }

}
