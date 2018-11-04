package org.bulpros.peakwork.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.bulpros.peakwork.persistence.IexDataStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.zankowski.iextrading4j.api.stocks.BatchStocks;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.BatchMarketStocksRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.BatchStocksType;

@Service
public class IexTradingStockServiceImpl implements IexTradingStockService{

	@Autowired
	IexDataStoreClient iexDataStoreClient;
	
	@Override
	public Supplier<Map<String, BatchStocks>> getStocks(String[] symbols) {
    	final IEXTradingClient iexTradingClient = IEXTradingClient.create();
    	
    	return () -> iexTradingClient.executeRequest(new BatchMarketStocksRequestBuilder()
    	        .withSymbols(Arrays.asList(symbols))
    	        .addType(BatchStocksType.PRICE)
    	        .addType(BatchStocksType.LOGO)
    	        .addType(BatchStocksType.KEY_STATS)
    	        .build());
	}

	@Override
	public List<String> retrieveStocks(String[] symbols, Date fromDate, Date toDate) {
		//todo wrap in filter
		return iexDataStoreClient.retrieveStock(symbols, fromDate, toDate);
			
		
	}

	@Override
	public Supplier<Map<String, BatchStocks>> storeStocks(Supplier<Map<String, BatchStocks>> stocks) {
		stocks.get().forEach((k,v) -> iexDataStoreClient.addStock(k,v));
		return null;
	}

}
