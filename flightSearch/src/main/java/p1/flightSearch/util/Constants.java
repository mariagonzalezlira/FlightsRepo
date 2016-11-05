package p1.flightSearch.util;

public final class Constants {
	
	//CSV File
	public static final String CSV_FILE = "flights.csv";

	//Airport Constants
	public static final String CODE_MAD = "MAD";
	public static final String CITY_MAD = "Madrid";
	public static final String CODE_BCN = "BCN";
	public static final String CITY_BCN = "Barcelona";
	public static final String CODE_LHR = "LHR";
	public static final String CITY_LHR = "London";
	public static final String CODE_CDG = "CDG";
	public static final String CITY_CDG = "Paris";
	public static final String CODE_FRA = "FRA";
	public static final String CITY_FRA = "Frakfurt";
	public static final String CODE_IST = "IST";
	public static final String CITY_IST = "Istanbul";
	public static final String CODE_AMS = "AMS";
	public static final String CITY_AMS = "Amsterdam";
	public static final String CODE_FCO = "FCO";
	public static final String CITY_FCO = "Rome";
	public static final String CODE_CPH = "CPH";
	public static final String CITY_CPH = "Copenhagen";
	public static final String CODE_SVQ = "SVQ";
	public static final String CITY_SVQ = "Sevilla";

	
	//Airline Constants
	public static final String CODE_IB = "IB";
	public static final Double INFANT_PRICE_IB =  new Double(10);
	public static final String CODE_BA = "BA";
	public static final Double INFANT_PRICE_BA =  new Double(15);
	public static final String CODE_LH = "LH";
	public static final Double INFANT_PRICE_LH =  new Double(7);
	public static final String CODE_FR = "FR";
	public static final Double INFANT_PRICE_FR =  new Double(20);
	public static final String CODE_VY = "VY";
	public static final Double INFANT_PRICE_VY =  new Double(10);
	public static final String CODE_TK = "TK";
	public static final Double INFANT_PRICE_TK =  new Double(5);
	public static final String CODE_U2 = "U2";
	public static final Double INFANT_PRICE_U2 =  new Double(19.90);
	
	//Multiplier depends of days to departure date to calculate the price
	public static final double MORE_30 = 0.8;
	public static final double BT_30_16 = 1;
	public static final double BT_15_3 = 1.2;
	public static final double LESS_3 = 1.5;
	
	//Multiplier for pasenger type to calculate the price
	public static final double ADULT_TYPE = 1; //full price
	public static final double CHILD_TYPE = 0.67; //33% discount of the price calculated according to the *days to departure date* rule

	
	
}
