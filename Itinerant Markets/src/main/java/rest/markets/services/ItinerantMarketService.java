package rest.markets.services;

import java.util.Vector;

import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

import rest.markets.resources.ItinerantMarket;
import rest.markets.utils.FieldStatistic;

public interface ItinerantMarketService {
	
	Vector<ItinerantMarket> getAll();
	Vector<ItinerantMarket> getRequestedItinerantMarket(ItinerantMarket requestedIM);
	JsonSchema getMetadata();
	Vector<FieldStatistic> getStats(String requestedStats);

}
