package com.bulpros.peakwork.entity;

import java.time.LocalDateTime;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * Instantiates a new stock.
 */
@Data
@Entity(name = "stock")
public class Stock {
	
    /** The stock record id. */
    @Id
    private Long stockRecordId;
    
    /** The symbol. */
    private String symbol;
    
    /** The company name. */
    private String companyName;
    
    /** The logo. */
    private String logo;
    
    /** The price. */
    private String price;
    
    /** The created. */
    private LocalDateTime created;
}
