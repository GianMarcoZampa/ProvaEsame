package rest.markets.utils.statistics;

import java.util.HashMap;
import java.util.Vector;

import org.springframework.stereotype.Service;

/**
 * It implements StatisticCalculus doing all the calculus used for the statistics.
 * @see StatisticCalcolus
 */
@Service
public class Stats implements StatisticCalcolus {

	/**
	 * Return the average value of the doubles in the Vector.
	 * @param toAvg is the vector of double 
	 * @return the average of the input vector
	 */
	@Override
	public double avg(Vector<Double> toAvg) {
		double avg = sum(toAvg)/toAvg.lastIndexOf(toAvg.lastElement());
		return avg;
	}

	/** Return the minimum value present in the Vector.
	 * @param toMin is the vector of double 
	 * @return the minimum value of the input vector
	 */
	@Override
	public double min(Vector<Double> toMin) {
		double min = toMin.firstElement();
		for(double i:toMin) {
			if(i<min) min = i;	
		}
		return min;
	}

	/** Return the maximum value present in the Vector. 
	 * @param toMax is the vector of double
	 * @return the maximum value of the input vector
	 */
	@Override
	public double max(Vector<Double> toMax) {
		double max = toMax.firstElement();
		for(double i:toMax) {
			if(i>max) max = i;	
		}
		return max;
	}

	/** Return the sum of the values present in the Vector.
	 * @param toSum is the vector of double
	 * @return the sum of all value in input vector
	 */
	@Override
	public double sum(Vector<Double> toSum) {
		double sum = 0;
		for(double i:toSum) {
			sum += i;
		}
		return sum;
	}
	
	/** Return the standard deviation of the values in the Vector.
	 * @param toStd is the vector of double
	 * @return the standard deviation of the input vector
	 */
	@Override
	public double std(Vector<Double> toStd) {
		double meanValue = avg(toStd);
		double std = 0;
		
		for(double i : toStd) {
			std += ((i-meanValue)*(i-meanValue));
		}
		std = toStd.lastIndexOf(toStd.lastElement());
		std = Math.sqrt(std);
		return std;
	}

	/** Return an HashMap which contains the number of repetitions(value) for each String(key)
	 * @param toRep vector of string where you are searching
	 * @return the number of repetition for each string in the input vector
	 */
	@Override
	public HashMap<String, Integer> repetition(Vector<String> toRep) {
		Integer integer;
		HashMap<String, Integer> returnHashValue = new HashMap<String, Integer>();
		for(String i: toRep) {
			if(returnHashValue.containsKey(i)) {
				integer = returnHashValue.get(i);
				integer += 1;
				returnHashValue.put(i, integer);
			}
			else returnHashValue.put(i, 0);
		}
		return returnHashValue;
	}


}
