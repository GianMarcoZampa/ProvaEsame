package rest.markets.utils;

import java.util.Vector;


public interface StatisticCalcolus {
	
	double avg(Vector<Double> toAvg);
	double min(Vector<Double> toMin);
	double max(Vector<Double> toMax);
	double sum(Vector<Double> toSum);

}
