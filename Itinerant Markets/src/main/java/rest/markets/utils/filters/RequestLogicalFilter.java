package rest.markets.utils.filters;

import java.util.Vector;

import rest.markets.resources.ItinerantMarket;

public class RequestLogicalFilter {

	private Vector<ItinerantMarket> param;
	private boolean in;
	
	// Constructor
	public RequestLogicalFilter(Vector<ItinerantMarket> param, boolean in) {
		this.param = param;
		this.in = in;
	}

	// Getters and Setters
	public Vector<ItinerantMarket> getParam() {
		return param;
	}
	public void setParam(Vector<ItinerantMarket> param) {
		this.param = param;
	}
	public boolean getIn() {
		return in;
	}
	public void setIn(boolean in) {
		this.in = in;
	}	
}
