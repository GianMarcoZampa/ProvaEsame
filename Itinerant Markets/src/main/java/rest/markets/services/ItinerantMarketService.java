package rest.markets.services;

import java.util.Vector;

import rest.markets.resources.ItinerantMarket;

public interface ItinerantMarketService {
	
	Vector<ItinerantMarket> getAll();

	Vector<ItinerantMarket> getRequestedItinerantMarket(ItinerantMarket requestedIM);
	
	void getMetadata();

}
