package org.bulpros.peakwork;

import org.bulpros.peakwork.service.IexTradingStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {
	
	@Autowired
	IexTradingStockService iexTradingStockService;

	@Value("${iex.symbols}")
	private String[] symbols;

	@Scheduled(fixedDelayString = "${iex.interval}")
	public void scheduleFixedDelayTask() {
		iexTradingStockService.storeStocks(iexTradingStockService.getStocks(symbols));
		log.info("Fixed delay task " + System.currentTimeMillis());
	}

}
