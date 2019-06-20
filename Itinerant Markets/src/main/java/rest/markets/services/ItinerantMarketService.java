package rest.markets.services;

import java.util.Vector;

import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

import rest.markets.resources.ItinerantMarket;
import rest.markets.utils.statistics.FieldStatistic;
import rest.markets.utils.filters.RequestConditionalFilter;
import rest.markets.utils.filters.RequestLogicalFilter;

/**Interface that includes everything that concerns the elaboration of itinerant markets data
 *
 */
public interface ItinerantMarketService {
	
	Vector<ItinerantMarket> getAll();
	JsonSchema getMetadata();
	Vector<FieldStatistic> getStats(String requestedStats);
	Vector<ItinerantMarket> getConditionalFilter(Vector<RequestConditionalFilter> requestedFilter);
	Vector<ItinerantMarket> getLogicalFilter(RequestLogicalFilter requestedFilter);
}
