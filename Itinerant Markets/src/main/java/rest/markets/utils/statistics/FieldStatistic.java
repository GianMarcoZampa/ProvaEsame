package rest.markets.utils.statistics;

/**
 * This class contains the field name for the statistics
 * @see NumFieldStatistics
 * @see StringFieldStatistics
 */
public class FieldStatistic {
	
	private String fieldName;

	public FieldStatistic(String fieldName) {
		this.fieldName = fieldName;
	}
	
	// Getters and setters
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
