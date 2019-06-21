package rest.markets.utils.filters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import org.springframework.stereotype.Service;

import rest.markets.resources.ItinerantMarket;
import rest.markets.resources.ItinerantMarketStats;

/**
 * This is the implementation of the interface Filter
 * @see Filter
 */
@Service
public class FilterImp implements Filter {

	/**
	 * This method creates a vector of elements with the matches field are greater or equal 
	 * than the input value "linf" (conditional filter)
	 * @param linf is the lower limits of the value
	 * @param equal is set as true if linf is included in the comparison,
	 * false otherwise
	 * @param data is the vector of elements you want to filter
	 * @param field is the selected field for filtering
	 * @return filteredMarket is a vector which contains all the elements in data with 
	 * field element greater (or equal if equal is true) than linf
	 * @throws IllegalArgumentException to indicate that a method has been passed an illegal 
	 * or inappropriate argument.
	 *@throws SecurityException when the user doesn't have the access to some file
	 */
	@Override
	public Vector<ItinerantMarket> gt(int linf, boolean equal, Vector<ItinerantMarket> data, String field) {
		
		Vector<ItinerantMarket> filteredMarkets = new Vector<ItinerantMarket>();
		String[] parsedField = field.split(",");
		Method getMethod1, getMethod2;
		

		// It happens if no split was made
		if(parsedField.length == 0) {
			getMethod1 = getRequestedMethod(ItinerantMarket.class, field);
		}
		else {
			getMethod1 = getRequestedMethod(ItinerantMarket.class, parsedField[0]);
			
		}

		// Parameters checking
		for(ItinerantMarket i:data) {
			try {
				if(equal) {
					int num;
					try {
						if(parsedField.length == 2) {
							getMethod2 = getRequestedMethod(ItinerantMarketStats.class, parsedField[1]);
							num = (Integer)(getMethod2.invoke(((ItinerantMarketStats)(getMethod1.invoke(i)))));
						}
						else {
							num = (Integer)(getMethod1.invoke(i));
						}
						if(num >= linf) filteredMarkets.add(i);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				else {
					int num;
					try {
						if(parsedField.length == 2) {
							getMethod2 = getRequestedMethod(ItinerantMarketStats.class, parsedField[1]);
							num = (Integer)(getMethod2.invoke(((ItinerantMarketStats)(getMethod1.invoke(i)))));
						}
						else {
							num = (Integer)(getMethod1.invoke(i));
						}
						if(num > linf) filteredMarkets.add(i);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		
		return filteredMarkets;
	}

	/**
	 * This method creates a vector of elements with the matches field are less or equal
	 * than the input value "lsup" (conditional filter)
	 * @param lsup is the upper limits of the value
	 * @param equal is set as true if lsup is included in the comparison,
	 * false otherwise
	 * @param data is the vector of elements you want to filter
	 * @param field is the selected field for filtering
	 * @return filteredMarket is a vector which contains all the elements in data with 
	 * field element greater (or equal if equal is true) than lsup
	 * @throws IllegalArgumentException to indicate that a method has been passed an illegal 
	 * or inappropriate argument.
	 *@throws SecurityException when the user doesn't have the access to some file
	 */
	
	@Override
	public Vector<ItinerantMarket> lt(int lsup, boolean equal, Vector<ItinerantMarket> data, String field) {
		
		Vector<ItinerantMarket> filteredMarkets = new Vector<ItinerantMarket>();
		String[] parsedField = field.split(",");
		Method getMethod1, getMethod2;
		
		
		// It happens if no split was made
		if(parsedField.length == 0) {
			getMethod1 = getRequestedMethod(ItinerantMarket.class, field);
		}
		else {
			getMethod1 = getRequestedMethod(ItinerantMarket.class, parsedField[0]);
		}

		
		// Parameters checking
		for(ItinerantMarket i:data) {
			try {
				if(equal) {
					int num;
					try {
						if(parsedField.length == 2) {
							getMethod2 = getRequestedMethod(ItinerantMarketStats.class, parsedField[1]);
							num = (Integer)(getMethod2.invoke(((ItinerantMarketStats)(getMethod1.invoke(i)))));
						}
						else {
							num = (Integer)(getMethod1.invoke(i));
						}
						if(num <= lsup) filteredMarkets.add(i);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				else {
					int num;
					try {
						if(parsedField.length == 2) {
							getMethod2 = getRequestedMethod(ItinerantMarketStats.class, parsedField[1]);
							num = (Integer)(getMethod2.invoke(((ItinerantMarketStats)(getMethod1.invoke(i)))));
						}
						else {
							num = (Integer)(getMethod1.invoke(i));
						}
						if(num < lsup) filteredMarkets.add(i);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		
		return filteredMarkets;
	}

	/**
	 * This method creates a vector of elements with the matches field included in the parameter
	 * linf and lsup (conditional filter)
	 * @param linf is the lower limits of the value
	 * @param lsup is the upper limits of the value
	 * @param equal is set as true if lsup is included in the comparison,
	 * false otherwise
	 * @param data is the vector of elements you want to filter
	 * @param field is the selected field for filtering
	 * @return filteredMarket is a vector which contains all the elements in data with 
	 * field element greater (or equal if equal is true) than lsup
	 * @throws IllegalArgumentException to indicate that a method has been passed an illegal 
	 * or inappropriate argument
	 * @throws SecurityException when the user doesn't have the access to some file
	 */
	
	@Override
	public Vector<ItinerantMarket> bt(int linf, int lsup, boolean equal, Vector<ItinerantMarket> data, String field) {
		Vector<ItinerantMarket> filteredMarkets = gt(linf, equal, data, field);
		filteredMarkets = lt(lsup, equal, filteredMarkets, field);
		return filteredMarkets;
	}

	/**
	 * This method creates a vector with all the elements in data that matches the ones in the requestedIM
	 * @param requestedIM is the parameter of the filter
	 * @param in if it's true then the elements that matches are added to the final vector, otrherwise 
	 * the matched elements are deleted from the final vector
	 * @param data vector that contains all the data to be filtered
	 * @return filteredMarkets is a vector which contain only the data that matches the requestedIM if in 
	 * is true, otherwise it contains only the data that doesn't match
	 */
	
	@Override
	public Vector<ItinerantMarket> in(ItinerantMarket requestedIM, boolean in, Vector<ItinerantMarket> data) {
		Vector<ItinerantMarket> filteredMarkets = new Vector<ItinerantMarket>();
		for(ItinerantMarket i : data) {
			if(in) {
				if(i.paramEquals(requestedIM)) filteredMarkets.add(i);
			}
			else {
				if(!i.paramEquals(requestedIM)) filteredMarkets.add(i);
			}
		}
		return filteredMarkets;

	}

	/**
	 * Method that saves the requestedClass
	 * @param requestedClass
	 * @param field
	 * @return requestedClass
	 */
	
	public <T> Method getRequestedMethod(Class<T> requestedClass, String field) {
		
		String methodName = "get" + field.substring(0,1).toUpperCase() + field.substring(1);
		
		try {
			return requestedClass.getMethod(methodName);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
