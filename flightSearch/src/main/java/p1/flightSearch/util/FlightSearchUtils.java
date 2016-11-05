package p1.flightSearch.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FlightSearchUtils {


	/**
	 * Read csv file to fill a List of flights
	 */
	public static List<FlightVO> readCsvFlightsFile(){
		
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<FlightVO> flightsList = new ArrayList<FlightVO>();

        try {

            br = new BufferedReader(new FileReader(Constants.CSV_FILE));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] csvLine = line.split(cvsSplitBy);
                
                FlightVO flight = new FlightVO();
                flight.setOrigin(csvLine[0]);
                flight.setDestination(csvLine[1]);
                flight.setAirline(csvLine[2]);
                flight.setBasePrice(new Double(csvLine[3]));
                
                // add flight to flight list
                flightsList.add(flight);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
        	System.out.println("Error: " + e.getMessage());
        }
		
        return flightsList;
	}
	
	/**
	 * Fill a Map with <key,value> with the Airports availables
	 */
	public static Map<String, String> getAirports(){
		Map<String, String> map = new HashMap<String, String>();
		map.put(Constants.CODE_MAD, Constants.CITY_MAD);
		map.put(Constants.CODE_BCN, Constants.CITY_BCN);
		map.put(Constants.CODE_LHR, Constants.CITY_LHR);
		map.put(Constants.CODE_CDG, Constants.CITY_CDG);
		map.put(Constants.CODE_FRA, Constants.CITY_FRA);
		map.put(Constants.CODE_IST, Constants.CITY_IST);
		map.put(Constants.CODE_AMS, Constants.CITY_AMS);
		map.put(Constants.CODE_FCO, Constants.CITY_FCO);
		map.put(Constants.CODE_CPH, Constants.CITY_CPH);
		return map;
	}
	
	/**
	 * Fill a Map with <key,value> with Airlines with infant prices
	 */
	public static Map<String, Double> getAirlinesInfantPrices(){
		Map<String, Double> map = new HashMap<String, Double>();
		map.put(Constants.CODE_IB, Constants.INFANT_PRICE_IB);
		map.put(Constants.CODE_BA, Constants.INFANT_PRICE_BA);
		map.put(Constants.CODE_LH, Constants.INFANT_PRICE_LH);
		map.put(Constants.CODE_FR, Constants.INFANT_PRICE_FR);
		map.put(Constants.CODE_VY, Constants.INFANT_PRICE_VY);
		map.put(Constants.CODE_TK, Constants.INFANT_PRICE_TK);
		map.put(Constants.CODE_U2, Constants.INFANT_PRICE_U2);
		return map;
	}
	
	/**
	 * Calculate the departure date based in the number of days to take the flight
	 * @param numDays from actual to departure date
	 * @return departureDate
	 */
	public static Date addDaysToCurrentDate(int numDays){
		
		//Departure Date calculation
		Calendar departureDate = new GregorianCalendar();
		departureDate.add(Calendar.DAY_OF_YEAR, numDays);	
		return new Date(departureDate.getTimeInMillis());
	}

	/**
	 * Calculate the number of days between current and departure date
	 * @param departure date
	 * @return number of days between today and departure date
	 */
	public static int getDaysToDeparture(Date departDate) {
		
		//Current Date calculation
		Calendar currentDate = new GregorianCalendar();
		int currentDayOfYear = currentDate.get(Calendar.DAY_OF_YEAR);
		
		//Departure Date calculation
		Calendar departureDate = new GregorianCalendar();
		departureDate.setTime(departDate);
		int departureDayOfYear = departureDate.get(Calendar.DAY_OF_YEAR);
		
		return departureDayOfYear - currentDayOfYear;
	}
}
