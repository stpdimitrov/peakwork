package org.bulpros.peakwork.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.StringValue;

import pl.zankowski.iextrading4j.api.stocks.BatchStocks;

@Component
public class IexDataStoreClient {

    // Create an authorized Datastore service using Application Default Credentials.
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
        Query query = new Query("stock");
        List<Filter> symbolFilters = new ArrayList<Filter>();
        for (int i = 1; i < symbols.length; i++) {
            symbolFilters.add(new Query.FilterPredicate("symbol", FilterOperator.EQUAL, symbols[i]));
        }
        Query.CompositeFilter symbolFilter = new Query.CompositeFilter(Query.CompositeFilterOperator.OR, symbolFilters);
        
        List<Filter> dateFilters = new ArrayList<Filter>();
        dateFilters.add(new Query.FilterPredicate("created", FilterOperator.GREATER_THAN_OR_EQUAL, fromDate));
        dateFilters.add(new Query.FilterPredicate("created", FilterOperator.LESS_THAN_OR_EQUAL, toDate));
        
        Query.CompositeFilter dateFilter = new Query.CompositeFilter(Query.CompositeFilterOperator.AND, dateFilters);
        
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(symbolFilter);
        filters.add(dateFilter);
        Query.CompositeFilter filter = new Query.CompositeFilter(Query.CompositeFilterOperator.AND, filters);
        
        query.setFilter(filter);
        query.addSort("created", SortDirection.ASCENDING);

        return formatStocks(datastoreService.prepare(query).asIterable());
    }

    /**
     * Converts a list of stocks entities to a list of formatted stocks strings.
     *
     * @param stocks
     *            An iterator over task entities
     * @return A list of tasks strings, one per entity
     */
    static List<String> formatStocks(Iterable<Entity> stocks) {
        List<String> strings = new ArrayList<>();
        for (Entity stock : stocks) {
            strings.add(stock.toString());
        }
        return strings;
    }

}
