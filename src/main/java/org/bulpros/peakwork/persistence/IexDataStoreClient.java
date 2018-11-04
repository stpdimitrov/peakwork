package org.bulpros.peakwork.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.cloud.Timestamp;
import com.google.cloud.datastore.StringValue;

import pl.zankowski.iextrading4j.api.stocks.BatchStocks;

@Component
public class 
IexDataStoreClient {

	private final DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

	public Key addStock(String symbol,BatchStocks stockData) {
		Entity stock = new Entity("stock");
				stock.setProperty("symbol", symbol);
				stock.setProperty("companyName", stockData.getKeyStats().getCompanyName());
				stock.setProperty("price", stockData.getPrice().toEngineeringString());
				stock.setProperty("logo", StringValue.newBuilder(stockData.getLogo().getUrl()).setExcludeFromIndexes(true).build());
				stock.setProperty("created", Timestamp.now());
				
		return datastoreService.put(stock);
	}

	public List<String> retrieveStock(String[] symbols, Date fromDate, Date toDate) {
//		
//		PropertyFilter[] propertyArray = new PropertyFilter[symbols.length-1];
//		for (int i = 1; i < symbols.length; i++) {
//			propertyArray[i-1] = PropertyFilter.eq("symbol", symbols[i]);
//		}
//		CompositeFilter companiesFilter = CompositeFilter.and(PropertyFilter.eq("symbol", symbols[0]),propertyArray);
//
//		
//		Query<Entity> query =
//			      Query.newEntityQueryBuilder()
//			      .setKind("stock")
//			      .setFilter(CompositeFilter.and(
//			    	        PropertyFilter.gt("created", Timestamp.of(fromDate)),
//			    	        PropertyFilter.lt("created", Timestamp.of(toDate))))
//			      .setFilter(companiesFilter)
//			      .setOrderBy(OrderBy.asc("created")).build();
//		
//		
//		List<String> list = Arrays.asList("Apple", "Facebook");
//		query.addFilter("companyName", FilterOperator.IN, list);
		
//		return formatStocks(datastore.run(query));
		
		return null;
	}
	
	  /**
	   * Converts a list of stocks entities to a list of formatted stocks strings.
	   *
	   * @param stocks An iterator over task entities
	   * @return A list of tasks strings, one per entity
	   */
	  static List<String> formatStocks(Iterator<Entity> stocks) {
	    List<String> strings = new ArrayList<>();
	    while (stocks.hasNext()) {
	      Entity stock = stocks.next();
	        strings.add(stock.toString());
	    }
	    return strings;
	  }

}
