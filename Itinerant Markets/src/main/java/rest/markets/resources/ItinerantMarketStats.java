package rest.markets.resources;

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

	// Equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ItinerantMarketStats)) return false;
		if(this.total != ((ItinerantMarketStats) obj).getTotal()) return false;
		if(this.attivations != ((ItinerantMarketStats) obj).getAttivations()) return false;
		if(this.cessations != ((ItinerantMarketStats) obj).getCessations()) return false;
		return true;
	}

}
