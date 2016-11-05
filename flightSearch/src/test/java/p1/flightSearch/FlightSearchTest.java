package p1.flightSearch;

import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import p1.flightSearch.search.SearchBean;
import p1.flightSearch.search.SearchCriteriaVO;
import p1.flightSearch.util.Constants;
import p1.flightSearch.util.FlightSearchUtils;

/**
 * Unit test for simple App.
 */
public class FlightSearchTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FlightSearchTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FlightSearchTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	try{
        	SearchBean bean = new SearchBean();
        	
        	//I calculate the departure date (as Date type) based on the number of days to the flight for the tests
        	
        	System.out.println("Search Criteria: 1 Adult, 31 days to the departure date, flying AMS -> FRA");
        	Date departDate31 = FlightSearchUtils.addDaysToCurrentDate(31);
        	System.out.println("Departure Date: " + departDate31.toString());
        	System.out.println("Flights:");
        	bean.searchFlights(new SearchCriteriaVO(Constants.CITY_AMS, Constants.CITY_FRA, departDate31, 1, 0, 0));
        	System.out.println("------------------------");
        	
        	System.out.println("Search Criteria: 2 Adults, 1 Child, 1 Infant, 15 days to the departure date, flying LHR -> IST");
        	Date departDate15 = FlightSearchUtils.addDaysToCurrentDate(15);
        	System.out.println("Departure Date: " + departDate15.toString());
        	System.out.println("Flights:");
        	bean.searchFlights(new SearchCriteriaVO(Constants.CITY_LHR, Constants.CITY_IST, departDate15, 2, 1, 1));
        	System.out.println("------------------------");
        	
        	System.out.println("Search Criteria: 1 Adult, 2 Children, 2 days to the departure date, flying BCN -> MAD");
        	Date departDate2 = FlightSearchUtils.addDaysToCurrentDate(2);
        	System.out.println("Departure Date: " + departDate2.toString());
        	System.out.println("Flights:");
        	bean.searchFlights(new SearchCriteriaVO(Constants.CITY_BCN, Constants.CITY_MAD, departDate2, 1, 2, 0));
        	System.out.println("------------------------");
        	
        	System.out.println("Search Criteria: CDG -> FRA");
        	System.out.println("Flights:");
        	bean.searchFlights(new SearchCriteriaVO(Constants.CITY_CDG, Constants.CITY_FRA, departDate31, 1, 0, 0));
        	System.out.println("------------------------");
        	
        	System.out.println("Search Criteria: MAD -> SVQ (SVQ is not available in the Airports list)");
        	System.out.println("Flights:");
        	bean.searchFlights(new SearchCriteriaVO(Constants.CITY_MAD, Constants.CITY_SVQ, departDate31, 1, 0, 0));
        	System.out.println("------------------------");       
        	
        	System.out.println("Search Criteria: 1 Adult, 2 Children, departure date 2 days ago, flying BCN -> MAD");
        	Date departDate2daysBefore = FlightSearchUtils.addDaysToCurrentDate(-2);
        	System.out.println("Departure Date: " + departDate2daysBefore.toString());
        	System.out.println("Flights:");
        	bean.searchFlights(new SearchCriteriaVO(Constants.CITY_BCN, Constants.CITY_MAD, departDate2daysBefore, 1, 2, 0));
        	System.out.println("------------------------");
        	        	
            assertTrue( true );
    	}catch(Exception e){
    		fail(e.getMessage());
    	}

    }
}
