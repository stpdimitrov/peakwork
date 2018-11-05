package com.bulpros.peakwork.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.bulpros.peakwork.entity.Stock;

import pl.zankowski.iextrading4j.api.stocks.BatchStocks;

/**
 * The Interface IexTradingStockService.
 */
public interface IexTradingStockService {

    /**
     * Fetch stocks.
     *
     * @param symbols the symbols
     * @return the supplier
     */
    Supplier<Map<String, BatchStocks>> fetchStocks(String[] symbols);

    /**
     * Store stocks.
     *
     * @param stocks the stocks
     */
    void storeStocks(Supplier<Map<String, BatchStocks>> stocks);

    /**
     * Retrieve stocks.
     *
     * @param symbols the symbols
     * @param fromDate the from date
     * @param toDate the to date
     * @return the list
     */
    List<Stock> retrieveStocks(String[] symbols, LocalDateTime fromDate, LocalDateTime toDate);

}
