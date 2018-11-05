package com.bulpros.peakwork.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bulpros.peakwork.entity.Stock;
import com.bulpros.peakwork.service.IexTradingStockService;

@RestController
public class IexTradingController {

    @Autowired
    IexTradingStockService iexTradingStockService;

    @RequestMapping(method = RequestMethod.GET, value = "/stocks/{symbols}/interval", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Stock> getStocks(@PathVariable String[] symbols,
            @RequestParam("from") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime toDate) {
        return iexTradingStockService.retrieveStocks(symbols, fromDate, toDate);
    }

}
