package rest.markets.utils.statistics;

import java.util.HashMap;

/**
 * This class inherits from {@link FieldStatistic} and it encapsulates 
 * the response to a string field statistic request.
 * @see FieldStatistic
 */
public class StringFieldStatistic extends FieldStatistic {

	private HashMap<String, Integer> repetition;
	
	// Constructor for String fields
	public StringFieldStatistic(String fieldName, HashMap<String, Integer> repetition) {
		super(fieldName);
		this.repetition = repetition;
	}

	// Getters and Setters
	public HashMap<String, Integer> getRepetition() {
		return repetition;
	}

	public void setRepetition(HashMap<String, Integer> repetition) {
		this.repetition = repetition;
	}

	
}
