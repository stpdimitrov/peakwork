package org.bulpros.peakwork.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.StringValue;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

import pl.zankowski.iextrading4j.api.stocks.BatchStocks;

@Component
public class 
IexDataStoreClient {

	// Create an authorized Datastore service using Application Default Credentials.
	private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

	// Create a Key factory to construct keys associated with this project.
	private final KeyFactory keyFactory = datastore.newKeyFactory().setKind("stock");

	public Key addStock(String symbol,BatchStocks stockData) {
		Key key = datastore.allocateId(keyFactory.newKey());
		Entity stock = Entity.newBuilder(key)
				.set("symbol", symbol)
				.set("companyName", stockData.getKeyStats().getCompanyName())
				.set("price", stockData.getPrice().toEngineeringString())
				.set("logo", StringValue.newBuilder(stockData.getLogo().getUrl()).setExcludeFromIndexes(true).build())
				.set("created", Timestamp.now())
				.build();
		datastore.put(stock);
		return key;
	}

	public List<String> retrieveStock(String[] symbols, Date fromDate, Date toDate) {
		
		PropertyFilter[] propertyArray = new PropertyFilter[symbols.length-1];
		for (int i = 1; i < symbols.length; i++) {
			propertyArray[i-1] = PropertyFilter.eq("symbol", symbols[i]);
		}
		CompositeFilter companiesFilter = CompositeFilter.and(PropertyFilter.eq("symbol", symbols[0]),propertyArray);

		
		Query<Entity> query =
			      Query.newEntityQueryBuilder()
			      .setKind("stock")
			      .setFilter(CompositeFilter.and(
			    	        PropertyFilter.gt("created", Timestamp.of(fromDate)),
			    	        PropertyFilter.lt("created", Timestamp.of(toDate))))
			      .setFilter(companiesFilter)
			      .setOrderBy(OrderBy.asc("created")).build();
		
		return formatStocks(datastore.run(query));
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
