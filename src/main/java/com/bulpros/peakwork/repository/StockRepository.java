package com.bulpros.peakwork.repository;

import java.util.List;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import com.bulpros.peakwork.entity.Stock;
import com.google.cloud.Timestamp;

public interface StockRepository extends DatastoreRepository<Stock, Long> {
    List<Stock> findAllBySymbolAndCreatedGreaterThanEqualAndCreatedLessThanEqualOrderByCreated(
            String symbol, Timestamp createdFrom, Timestamp createdTo);
    
    List<Stock> findAllBySymbol(String symbol);
    
    List<Stock> findAllByCreatedGreaterThanEqualAndCreatedLessThanEqualOrderByCreated(
            Timestamp createdFrom, Timestamp createdTo);
}
