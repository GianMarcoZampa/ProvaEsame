package rest.markets.utils.filters;

/**In this class there are all the parameters for filtering data with a conditional filter
 *
 */
public class RequestConditionalFilter {
	
	private String nameField;
	private String filterType;
	private boolean equal;
	
	// Constructor
	public RequestConditionalFilter(String nameField, String filterType, boolean equal) {
		this.nameField = nameField;
		this.filterType = filterType;
		this.equal = equal;
	}
	
	// Getters and Setters
	public String getNameField() {
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	public boolean getEqual() {
		return equal;
	}
	public void setEqual(boolean equal) {
		this.equal = equal;
	}
}
