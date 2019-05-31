package rest.markets.utils;

import java.util.HashMap;

public class StringFieldStatistic extends FieldStatistic {

	private HashMap<String, Integer> repetition;
	
	// Constructor for String fields
	public StringFieldStatistic(String fieldName, HashMap<String, Integer> repetition) {
		super(fieldName);
		this.repetition = repetition;
	}

	public HashMap<String, Integer> getRepetition() {
		return repetition;
	}

	public void setRepetition(HashMap<String, Integer> repetition) {
		this.repetition = repetition;
	}

	
}
