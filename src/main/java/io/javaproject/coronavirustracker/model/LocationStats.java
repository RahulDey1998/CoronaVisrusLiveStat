package io.javaproject.coronavirustracker.model;

public class LocationStats {
	
	private String state;
	private String country;
    private int lastestTotalCases;
    private int diffFromPrevDay;
    
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLastestTotalCases() {
		return lastestTotalCases;
	}
	public void setLastestTotalCases(int lastestTotalCases) {
		this.lastestTotalCases = lastestTotalCases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", lastestTotalCases=" + lastestTotalCases
				+ "]";
	}
    
    

}
