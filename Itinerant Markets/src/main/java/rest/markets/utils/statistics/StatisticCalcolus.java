package rest.markets.utils.statistics;

import java.util.HashMap;
import java.util.Vector;

/**
 * This interface includes all the abstract methods to calculate statistics,
 * for numerical and string fields.
 * @see Stats
 */
public interface StatisticCalcolus {
	
	double avg(Vector<Double> toAvg);
	double min(Vector<Double> toMin);
	double max(Vector<Double> toMax);
	double sum(Vector<Double> toSum);
	double std(Vector<Double> toStd);
	HashMap<String, Integer> repetition(Vector<String> toRep);

}
