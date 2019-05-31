package rest.markets.utils;

import java.util.Vector;

import org.springframework.stereotype.Service;

@Service
public class Stats implements StatisticCalcolus {

	// Return the average value of the doubles in the Vector
	@Override
	public double avg(Vector<Double> toAvg) {
		double avg = sum(toAvg)/toAvg.lastIndexOf(toAvg.lastElement());
		return avg;
	}

	// Return the minimum value present in the Vector
	@Override
	public double min(Vector<Double> toMin) {
		double min = toMin.firstElement();
		for(double i:toMin) {
			if(i<min) min = i;	
		}
		return min;
	}

	// Return the maximum value present in the Vector 
	@Override
	public double max(Vector<Double> toMax) {
		double max = toMax.firstElement();
		for(double i:toMax) {
			if(i>max) max = i;	
		}
		return max;
	}

	// Return the sum of the values present in the Vector
	@Override
	public double sum(Vector<Double> toSum) {
		double sum = 0;
		for(double i:toSum) {
			sum += i;
		}
		return sum;
	}

}
