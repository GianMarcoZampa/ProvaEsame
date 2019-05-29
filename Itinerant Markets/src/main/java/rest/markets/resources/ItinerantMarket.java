package rest.markets.resources;

public class ItinerantMarket {
	
	private String comune;
	private String provincia;
	private int year;
	private ItinerantMarketStats totalStats;
	private ItinerantMarketStats foodStats;
	private ItinerantMarketStats notFoodStats;
	private ItinerantMarketStats prodStats;
	
	// Constructors
	public ItinerantMarket(String comune, String provincia, int year, ItinerantMarketStats totalStats,
			ItinerantMarketStats foodStats, ItinerantMarketStats notFoodStats, ItinerantMarketStats prodStats) {
		this.comune = comune;
		this.provincia = provincia;
		this.year = year;
		this.totalStats = totalStats;
		this.foodStats = foodStats;
		this.notFoodStats = notFoodStats;
		this.prodStats = prodStats;
	}

	public ItinerantMarket() {}
	
	// Getters and Setters
	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public ItinerantMarketStats getTotalStats() {
		return totalStats;
	}

	public void setTotalStats(ItinerantMarketStats totalStats) {
		this.totalStats = totalStats;
	}

	public ItinerantMarketStats getFoodStats() {
		return foodStats;
	}

	public void setFoodStats(ItinerantMarketStats foodStats) {
		this.foodStats = foodStats;
	}

	public ItinerantMarketStats getNotFoodStats() {
		return notFoodStats;
	}

	public void setNotFoodStats(ItinerantMarketStats notFoodStats) {
		this.notFoodStats = notFoodStats;
	}

	public ItinerantMarketStats getProdStats() {
		return prodStats;
	}

	public void setProdStats(ItinerantMarketStats prodStats) {
		this.prodStats = prodStats;
	}
	
	// ToString
	@Override
	public String toString() {
		return "ItinerantMarket [comune=" + comune + ", provincia=" + provincia + ", year=" + year + ", totalStats="
				+ totalStats + ", foodStats=" + foodStats + ", notFoodStats=" + notFoodStats + ", prodStats="
				+ prodStats + "]";
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ItinerantMarket)) return false;
		if(!(this.comune.equals(((ItinerantMarket) obj).getComune()))) return false;
		if(!(this.provincia.equals(((ItinerantMarket) obj).getProvincia()))) return false;
		if(this.year != ((ItinerantMarket) obj).getYear()) return false;
		if(!(this.totalStats.equals(((ItinerantMarket) obj).getTotalStats()))) return false;
		if(!(this.foodStats.equals(((ItinerantMarket) obj).getFoodStats()))) return false;
		if(!(this.notFoodStats.equals(((ItinerantMarket) obj).getNotFoodStats()))) return false;
		if(!(this.prodStats.equals(((ItinerantMarket) obj).getProdStats()))) return false;
		else return true;
	}

	
}
