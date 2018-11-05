package com.bulpros.peakwork.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.bulpros.peakwork.entity.Stock;

import pl.zankowski.iextrading4j.api.stocks.BatchStocks;

public interface IexTradingStockService {

    Supplier<Map<String, BatchStocks>> fetchStocks(String[] symbols);

    void storeStocks(Supplier<Map<String, BatchStocks>> stocks);

    List<Stock> retrieveStocks(String[] symbols, LocalDateTime fromDate, LocalDateTime toDate);

}
