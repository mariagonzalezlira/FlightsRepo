package p1.flightSearch.search;

import java.util.Date;

/**
 * @author Maria Gonzalez Lira
 * This class is represent a Value Object for the search criteria.
 * It only contains attributes, getters, setters and constructor
 */
public class SearchCriteriaVO {
	
	private String origin;
	private String destination;
	private Date date;
	private int adults;
	private int childs;
	private int infants;

	public SearchCriteriaVO() {
		// TODO Auto-generated constructor stub
	}
	
	public SearchCriteriaVO(String origin, String destination, Date date, int numAdults, int numChilds, int numInfants) {
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.adults = numAdults;
		this.childs = numChilds;
		this.infants = numInfants;
	}

	
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChilds() {
		return childs;
	}

	public void setChilds(int childs) {
		this.childs = childs;
	}

	public int getInfants() {
		return infants;
	}

	public void setInfants(int infants) {
		this.infants = infants;
	}

	
}
