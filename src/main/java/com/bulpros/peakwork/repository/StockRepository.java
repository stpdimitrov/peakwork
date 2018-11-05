package com.bulpros.peakwork.repository;

import java.util.List;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import com.bulpros.peakwork.entity.Stock;
import com.google.cloud.Timestamp;

/**
 * The Interface StockRepository.
 */
public interface StockRepository extends DatastoreRepository<Stock, Long> {
    
    /**
     * Find all by symbol and created greater than equal and created less than equal order by created.
     *
     * @param symbol the symbol
     * @param createdFrom the created from
     * @param createdTo the created to
     * @return the list
     */
    List<Stock> findAllBySymbolAndCreatedGreaterThanEqualAndCreatedLessThanEqualOrderByCreated(
            String symbol, Timestamp createdFrom, Timestamp createdTo);
    
    /**
     * Find all by symbol.
     *
     * @param symbol the symbol
     * @return the list
     */
    List<Stock> findAllBySymbol(String symbol);
    
    /**
     * Find all by created greater than equal and created less than equal order by created.
     *
     * @param createdFrom the created from
     * @param createdTo the created to
     * @return the list
     */
    List<Stock> findAllByCreatedGreaterThanEqualAndCreatedLessThanEqualOrderByCreated(
            Timestamp createdFrom, Timestamp createdTo);
}
