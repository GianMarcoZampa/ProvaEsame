package rest.markets.utils.filters;

import java.util.Vector;

import rest.markets.resources.ItinerantMarket;

/**This class implement a logical filter request using a vector of 
 * {@link rest#markets#resources#ItinerantMarket} and a boolean variable. 
 * The vector of parameter contains the desired field values and the 
 * boolean variable indicates if you want to add or to eliminate them.
 * @see Filter
 * @see RequestLogicalFilter
 */
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
