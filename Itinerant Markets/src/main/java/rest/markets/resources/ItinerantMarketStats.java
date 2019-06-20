package rest.markets.resources;

/**This is a class with the statistics of itinerant market. It contains the total number,
 * the number of attivation and the number of cessation
 */
public class ItinerantMarketStats {
	
	private int total;
	private int attivations;
	private int cessations;

	// Constructors
	public ItinerantMarketStats(int total, int attivations, int cessations) {
		this.total = total;
		this.attivations = attivations;
		this.cessations = cessations;
	}
	
	public ItinerantMarketStats() {}

	// Getters and Setters
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAttivations() {
		return attivations;
	}

	public void setAttivations(int attivations) {
		this.attivations = attivations;
	}

	public int getCessations() {
		return cessations;
	}

	public void setCessations(int cessations) {
		this.cessations = cessations;
	}
	
	// ToString
	@Override
	public String toString() {
		return "ItinerantMarketStats [total=" + total + ", attivations=" + attivations + ", cessations=" + cessations
				+ "]";
	}

	/**Method used for comparison.
	 * <p>
	 * @param obj Object received with the controller
	 * @return true if the input object equals the itinerantMarket object in every field, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ItinerantMarketStats)) return false;
		if(this.total != ((ItinerantMarketStats) obj).getTotal()) return false;
		if(this.attivations != ((ItinerantMarketStats) obj).getAttivations()) return false;
		if(this.cessations != ((ItinerantMarketStats) obj).getCessations()) return false;
		return true;
	}

}
