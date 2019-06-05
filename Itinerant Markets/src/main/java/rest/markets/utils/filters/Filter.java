package rest.markets.utils.filters;

import java.util.Vector;

import rest.markets.resources.ItinerantMarket;

public interface Filter {
	
	// Numerical filters
	Vector<ItinerantMarket> gt(int linf, boolean equal, Vector<ItinerantMarket> data, String field);
	Vector<ItinerantMarket> lt(int lsup, boolean equal, Vector<ItinerantMarket> data, String field);
	Vector<ItinerantMarket> bt(int linf, int lsup, boolean equal, Vector<ItinerantMarket> data, String field);
	
	// Logical filters
	Vector<ItinerantMarket> in(ItinerantMarket requestedIM, boolean in, Vector<ItinerantMarket> data);
	
}
