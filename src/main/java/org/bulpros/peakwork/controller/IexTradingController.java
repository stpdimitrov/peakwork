package org.bulpros.peakwork.controller;

import java.util.Date;
import java.util.List;

import org.bulpros.peakwork.service.IexTradingStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IexTradingController {

	@Autowired
	IexTradingStockService iexTradingStockService;

	@RequestMapping(method = RequestMethod.GET, value = "/stocks/{symbols}/interval", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> getStocks(@PathVariable String[] symbols,
			@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
			@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
		 return iexTradingStockService.retrieveStocks(symbols, fromDate, toDate);
	}

}
