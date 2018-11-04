package org.bulpros.peakwork.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import pl.zankowski.iextrading4j.api.stocks.BatchStocks;


public interface IexTradingStockService {
	
	Supplier<Map<String, BatchStocks>> getStocks(String[] symbols);
	
	Supplier<Map<String, BatchStocks>> storeStocks(Supplier<Map<String, BatchStocks>> stocks);
	
	List<String> retrieveStocks(String[] symbols, Date fromDate, Date toDate);

}
