package com.bulpros.peakwork.entity;

import java.time.LocalDateTime;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Entity(name = "stock")
public class Stock {
	
    @Id
    private Long stockRecordId;
    private String symbol;
    private String companyName;
    private String logo;
    private String price;
    private LocalDateTime created;
}
