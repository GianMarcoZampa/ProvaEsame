package rest.markets.utils.statistics;

/**
 * This class contains the field name for the statistics
 * @see NumFieldStatistic
 * @see StringFieldStatistic
 */
public class FieldStatistic {
	
	// Attributes
	private String fieldName;

	// Constructor
	public FieldStatistic(String fieldName) {
		this.fieldName = fieldName;
	}
	
	// Getter and setter
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
