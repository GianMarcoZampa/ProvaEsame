package rest.markets.utils.statistics;

/**
 * This class inherits from {@link FieldStatistic} and it encapsulates 
 * the response to a numeric field statistic request.
 * @see FieldStatistic
 */
public class NumFieldStatistic extends FieldStatistic {

	private String avg;
	private String min;
	private String max;
	private String sum;
	private String std;
	
	// Constructor for numeric fields
	public NumFieldStatistic(String fieldName, String avg, String min, String max, String sum, String std) {
		super(fieldName);
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.sum = sum;
		this.std = std;
	}
	
	// Getters and Setters
	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	
	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}
}
