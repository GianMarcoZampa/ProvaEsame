package rest.markets.services;

import java.util.Vector;

import rest.markets.resources.ItinerantMarket;

public interface ItinerantMarketService {
	
	ItinerantMarket getItinerantMarket(ItinerantMarket requestedIM);

	Vector<ItinerantMarket> getAll();

}
