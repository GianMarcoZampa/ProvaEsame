package rest.markets.utils.filters;

import java.util.Vector;

import org.springframework.stereotype.Service;

import rest.markets.resources.ItinerantMarket;

//@Service
public class FilterImplementation implements Filter {

	@Override
	public Vector<ItinerantMarket> gt(int linf, boolean equal, Vector<ItinerantMarket> data, String field) {
		Vector<ItinerantMarket> filteredMarkets = new Vector<ItinerantMarket>(); 
		switch(field) {
		case "year":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getYear() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getYear() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "totalStats.total":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getTotalStats().getTotal() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getTotalStats().getTotal() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "totalStats.attivations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getTotalStats().getAttivations() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getTotalStats().getAttivations() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "totalStats.cessations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getTotalStats().getCessations() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getTotalStats().getCessations() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "foodStats.total":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getFoodStats().getTotal() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getFoodStats().getTotal() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "foodStats.attivations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getFoodStats().getAttivations() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getFoodStats().getAttivations() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "foodStats.cessations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getFoodStats().getCessations() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getFoodStats().getCessations() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "notFoodStats.total":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getNotFoodStats().getTotal() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getNotFoodStats().getTotal() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "notFoodStats.attivations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getNotFoodStats().getAttivations() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getNotFoodStats().getAttivations() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "notFoodStats.cessations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getNotFoodStats().getCessations() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getNotFoodStats().getCessations() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "prodStats.total":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getProdStats().getTotal() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getProdStats().getTotal() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "prodStats.attivations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getProdStats().getAttivations() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getProdStats().getAttivations() > linf) filteredMarkets.add(i);
				}
			}
			break;
		case "prodStats.cessations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getProdStats().getCessations() >= linf) filteredMarkets.add(i);
				}
				else {
					if(i.getProdStats().getCessations() > linf) filteredMarkets.add(i);
				}
			}
			break;
		default: break;
		}
		
		return filteredMarkets;
	}

	@Override
	public Vector<ItinerantMarket> lt(int lsup, boolean equal, Vector<ItinerantMarket> data, String field) {
		
		Vector<ItinerantMarket> filteredMarkets = new Vector<ItinerantMarket>(); 
		switch(field) {
		case "year":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getYear() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getYear() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "totalStats.total":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getTotalStats().getTotal() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getTotalStats().getTotal() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "totalStats.attivations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getTotalStats().getAttivations() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getTotalStats().getAttivations() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "totalStats.cessations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getTotalStats().getCessations() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getTotalStats().getCessations() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "foodStats.total":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getFoodStats().getTotal() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getFoodStats().getTotal() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "foodStats.attivations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getFoodStats().getAttivations() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getFoodStats().getAttivations() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "foodStats.cessations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getFoodStats().getCessations() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getFoodStats().getCessations() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "notFoodStats.total":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getNotFoodStats().getTotal() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getNotFoodStats().getTotal() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "notFoodStats.attivations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getNotFoodStats().getAttivations() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getNotFoodStats().getAttivations() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "notFoodStats.cessations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getNotFoodStats().getCessations() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getNotFoodStats().getCessations() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "prodStats.total":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getProdStats().getTotal() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getProdStats().getTotal() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "prodStats.attivations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getProdStats().getAttivations() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getProdStats().getAttivations() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		case "prodStats.cessations":
			for(ItinerantMarket i : data) {
				if(equal) {
					if(i.getProdStats().getCessations() <= lsup) filteredMarkets.add(i);
				}
				else {
					if(i.getProdStats().getCessations() < lsup) filteredMarkets.add(i);
				}
			}
			break;
		default: break;
		}
		
		return filteredMarkets;

	}

	@Override
	public Vector<ItinerantMarket> bt(int linf, int lsup, boolean equal, Vector<ItinerantMarket> data, String field) {
		Vector<ItinerantMarket> filteredMarkets = gt(linf, equal, data, field);
		filteredMarkets = lt(lsup, equal, filteredMarkets, field);
		return filteredMarkets;
	}

	// For-each cycle if it found an ItinerantMarket that accomplish the requested
	// property, it add that value to the vector itMaVec
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

}
