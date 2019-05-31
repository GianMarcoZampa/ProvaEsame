package rest.markets.utils;

public class FieldStatistic {
	
	String fieldName;
	String avg;
	String min;
	String max;
	String sum;
	
	public FieldStatistic(String fieldName, String avg, String min, String max, String sum) {
		this.fieldName = fieldName;
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.sum = sum;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

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

}
