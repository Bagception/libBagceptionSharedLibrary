package de.uniulm.bagception.services.attributes;


public class Calendar {
	
	/**
	 * Specifies the request given to our CalendarService
	 * 1. CALENDAR_EVENTS: if you want to request a list of calendar events
	 * 2. CALENDAR_NAMES: if you want to request a list of the Android Calendar names
	 */
	public static final String REQUEST_TYPE = "requestType";
	
	/**
	 * 1. List of Android Calendar names for an intent service as a limit for the returned calendar events
	 * 2. List of Android Calendar names returned from our own CalendarService
	 */
	public static final String CALENDAR_NAMES = "calendarNames";
	
	/**
	 *  Specifies an ArrayList<String> of returned CalendarEvents
	 */
	public static final String CALENDAR_EVENTS = "calendarEvents";
	
	/**
	 * Specifies a string array of Android Calendar IDs
	 */
	public static final String CALENDAR_IDS = "calendarIDs";
	
	/**
	 * Limits the maximum number of calendar events which should be returned.
	 */
	public static final String NUMBER_OF_EVENTS = "numberOfEvents";
	
	
}
