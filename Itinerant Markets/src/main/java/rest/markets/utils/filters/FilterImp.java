package rest.markets.utils.filters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import org.springframework.stereotype.Service;

import rest.markets.resources.ItinerantMarket;
import rest.markets.resources.ItinerantMarketStats;

@Service
public class FilterImp implements Filter {

	// Greater than filter
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

	// Less than filter
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

	@Override
	public Vector<ItinerantMarket> bt(int linf, int lsup, boolean equal, Vector<ItinerantMarket> data, String field) {
		Vector<ItinerantMarket> filteredMarkets = gt(linf, equal, data, field);
		filteredMarkets = lt(lsup, equal, filteredMarkets, field);
		return filteredMarkets;
	}

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

	// This method returns the get method of the field and class requested if exists else it returns null
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
