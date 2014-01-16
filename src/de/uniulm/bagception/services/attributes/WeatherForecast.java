package de.uniulm.bagception.services.attributes;

public class WeatherForecast {
	
	/**
	 * Specifies the latitude of a geographical position
	 * <br> Use with Service Intent
	 */
	public static final String LATITUDE = "lat";
	
	/**
	 * specifies the longitude of a geographical position
	 * <br> Use with Service Intent
	 */
	public static final String LONGITUDE = "lng";
	
	/**
	 * defines the unit in which the answer should be returned
	 * <br> Use with Service Intent
	 * <br> 1. metric
	 * <br> 2. imperial
	 */
	public static final String UNIT = "unit";
	
	/**
	 * Name of the city of a given position
	 * <br> Use with ReceiveResult
	 */
	public static final String CITY = "city";
	
	/**
	 * Temperature of a given position
	 * <br> Use with ReceiveResult
	 */
	public static final String TEMP = "temp";
	
	/**
	 * Minimal temperature of a given position
	 * <br> Use with ReceiveResult
	 */
	public static final String TEMP_MIN	= "tempMin";
	
	/**
	 * Maximum temperature of a given position
	 * <br> Use with ReceiveResult
	 */
	public static final String TEMP_MAX	= "tempMax";
	
	/**
	 * Wind speed of a given position
	 * <br> Use with ReceiveResult
	 */
	public static final String WIND = "wind";
	
	/**
	 * Rain probability of a given position
	 * <br> Use with ReceiveResult
	 */
	public static final String RAIN = "rain";
	
	/**
	 * Cloudiness of a given position
	 * <br> Use with ReceiveResult
	 */
	public static final String CLOUDS = "clouds";
	

}
