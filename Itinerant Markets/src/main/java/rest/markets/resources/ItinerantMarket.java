package rest.markets.resources;

import java.util.Arrays;

public class ItinerantMarket {
	
	private String comune;
	private String provincia;
	private int year;
	private ItinerantMarketStats[] stats = new ItinerantMarketStats[4];
	
	// Constructors
	public ItinerantMarket(String comune, String provincia, int year, ItinerantMarketStats[] stats) {
		this.comune = comune;
		this.provincia = provincia;
		this.year = year;
		this.stats = stats;
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

	public ItinerantMarketStats[] getStats() {
		return stats;
	}

	public void setStats(ItinerantMarketStats[] stats) {
		this.stats = stats;
	}
	
	// ToString
	@Override
	public String toString() {
		return "ItinerantMarket [comune=" + comune + ", provincia=" + provincia + ", anno=" + year + ", stats="
				+ Arrays.toString(stats) + "]";
	}
	

	
}
