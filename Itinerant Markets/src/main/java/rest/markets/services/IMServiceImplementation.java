package rest.markets.services;

import java.util.Vector;

import rest.markets.resources.ItinerantMarket;

public class IMServiceImplementation implements ItinerantMarketService {
	
	// This Vector contains all the data from the file ItinerantMarket.csv
	Vector<ItinerantMarket> itMaList = new Vector<ItinerantMarket>();

	private Vector<ItinerantMarket> CreateList() {
		
		return null;
	}
	
	// This method returns the requested ItinerantMarket if it is present in the file
	@Override
	public ItinerantMarket getItinerantMarket(ItinerantMarket requestedIM) {
		
		// If the list is empty a new one is created form the file
		if(itMaList.isEmpty()) itMaList = CreateList();
		
		// For-each cycle if it found the requested itinerant market returns that value
		// else return null
		for(ItinerantMarket itMarket:itMaList) {
			if(itMarket.equals(requestedIM)) return itMarket;
		}
		
		return null;
	}

}
