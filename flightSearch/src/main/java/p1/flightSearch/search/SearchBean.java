/**
 * 
 */
package p1.flightSearch.search;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import p1.flightSearch.util.Constants;
import p1.flightSearch.util.FlightSearchUtils;
import p1.flightSearch.util.FlightVO;

/**
 * @author Maria Gonzalez Lira
 * Even this class is not a Bean, I named it SearchBean because in the 
 * future it could be a service and publish methods in an EJB
 */
public class SearchBean {
	
	//Global Attributes
	List<FlightVO> flightList;
	Map<String, String> airports;
	Map<String, Double> airlinesInfantPrices;


	/**
	 * Auto generated constructor
	 */
	public SearchBean() {
	}
	
	/**
	 * Search fligths from search criteria
	 * @param SearchCriteriaVO criteria
	 * @return List<ResultVO> list of flights that match with the search criteria
	 */
	public List<ResultVO> searchFlights(SearchCriteriaVO criteria){
		
		//Declaration of the result object
		List<ResultVO> resultList = null;
		
		//Load data
		loadData();
		
		//Search flights according to input data
		if(flightList != null && !flightList.isEmpty()){
			resultList = new ArrayList<ResultVO>();
			//Calculation of days to departure
			int daysDeparture = FlightSearchUtils.getDaysToDeparture(criteria.getDate());
			if(daysDeparture < 0){
				System.out.println("Departure date must be higher than actual");
			}else{
				for (Iterator iterator = flightList.iterator(); iterator.hasNext();) {
					FlightVO flightVO = (FlightVO) iterator.next();
					
					if(flightVO.getOrigin().equalsIgnoreCase(findAirportKey(criteria.getOrigin())) && flightVO.getDestination().equalsIgnoreCase(findAirportKey(criteria.getDestination()))){
						resultList.add(calculatePrice(criteria, flightVO, daysDeparture));
					}		
				}
			}if(resultList.isEmpty()){
				System.out.println("No flights available");
			}
			
		}else{
			System.out.println("No flights available");
		}
		
		return resultList;
	
	}


	/**
	 * Return the Airport code with the city name as input parameter
	 * @param city
	 * @return String
	 */
	private String findAirportKey(String city) {
		String key = null;
		for (Map.Entry<String, String> airport : airports.entrySet())
		{
		    if(!isNullOrEmpty(airport.getValue()) && airport.getValue().equals(city)){
		    	key = airport.getKey();
		    	break;
		    }
		}		
		return key;
	}

	/**
	 * Determine the total price of the flight for all passengers
	 * @param SearchCriteriaVO criteria
	 * @param FlightVO flightVO
	 */
	private ResultVO calculatePrice(SearchCriteriaVO criteria, FlightVO flightVO, int daysDeparture) {
		ResultVO result = new ResultVO();
		double totalPrice = 0;
		if(criteria.getAdults() != 0){
			totalPrice += calculateAdultsPrice(daysDeparture, criteria.getAdults(), flightVO.getBasePrice());
		}
		if(criteria.getChilds() != 0){
			totalPrice += calculateChildrenPrice(daysDeparture, criteria.getChilds(), flightVO.getBasePrice());
		}
		if(criteria.getInfants() != 0){
			totalPrice += calculateInfantsPrice(criteria.getInfants(), flightVO.getAirline());
		}
		result.setFlightCode(flightVO.getAirline());
		result.setTotalPrice(totalPrice);
		
		System.out.println("Flight Code: " + result.getFlightCode() + " - Total price: " + result.getTotalPrice() + " €");

		
		return result;
		
	}
	
	//Fixed price depending on the airline. Rule *days to departure date* is not applied for infants
	public double calculateInfantsPrice(int infants, String flightCode) {
		double price = 0;
		String airlineCode = getAirlineCode(flightCode);
		//Search for airline infants prices	
		price = (airlinesInfantPrices.get(airlineCode) != null ? airlinesInfantPrices.get(airlineCode)*infants : price);
			
		return price;
	}

	//The 2 first characters of flightCode correspond to the Airline code.
	private String getAirlineCode(String flightCode) {
		if(!isNullOrEmpty(flightCode))
			return flightCode.substring(0,2);
		else
			return null;
	}

	//33% discount of the price calculated according to the *days to departure date* rule 
	private double calculateChildrenPrice(int daysDeparture, int childs, double basePrice) {
		double price = 0;
		double multiplier = getMultiplier(daysDeparture);
		price = basePrice * multiplier * Constants.CHILD_TYPE * childs;
		return price;	
	}

	//full price (i.e. price resulting from the *days to departure date* rule)        
	private double calculateAdultsPrice(int daysDeparture, int adults, double basePrice) {
		double price = 0;
		double multiplier = getMultiplier(daysDeparture);
		price = basePrice * multiplier * adults;
		return price;	
	}

	/**
	 * Gets de multiplier that depends on the departure date
	 * @param daysDeparture
	 * @return multiplier
	 */
	private double getMultiplier(int daysDeparture) {
		double multiplier = 1;
		if (daysDeparture > 30){
			multiplier = Constants.MORE_30;
		}else if(daysDeparture >= 16 && daysDeparture <= 30){
			multiplier = Constants.BT_30_16;
		}else if(daysDeparture >= 3 && daysDeparture <= 15){
			multiplier = Constants.BT_15_3;
		}else{
			multiplier = Constants.LESS_3;
		}	
		return multiplier;
	}

	/**
	 * Load given info for the business logic
	 */
	private void loadData() {
		this.flightList = FlightSearchUtils.readCsvFlightsFile();
		this.airports = FlightSearchUtils.getAirports();
		this.airlinesInfantPrices = FlightSearchUtils.getAirlinesInfantPrices();
		
	}
	
	/**
	 * Determines if a String is empty or null
	 * @param String data
	 * @return boolean
	 */
	private boolean isNullOrEmpty(String data){
		boolean isNull = false;
		if(data.isEmpty() || data == null){
			isNull = true;
		}
		return isNull;
	}

}
