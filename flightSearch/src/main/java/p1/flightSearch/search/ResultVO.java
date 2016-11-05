package p1.flightSearch.search;

import java.math.BigDecimal;

/**
 * @author Maria Gonzalez Lira
 * This class is represent a Value Object for the search result.
 * It only contains attributes, getters, setters and constructor
 */
public class ResultVO {

	private String flightCode;
	private double totalPrice;
	
	public ResultVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultVO(String flightCode, double totalPrice){
		this.flightCode = flightCode;
		this.totalPrice = totalPrice;
		
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public double getTotalPrice() {	
		return Math.floor(totalPrice*100)/100; //We only want to see 2 decimals
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	
}
