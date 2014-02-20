package de.uniulm.bagception.services.attributes;


public class Calendar {
	
	/**
	 * Specifies the request given to our CalendarService
	 * <br>1. CALENDAR_EVENTS: if you want to request a list of calendar events
	 * <br>2. CALENDAR_NAMES: if you want to request a list of the Android Calendar names
	 */
	public static final String REQUEST_TYPE = "requestType";
	
	public static final String RESPONSE_TYPE = "responseType";
	
	/**
	 * 1. List of Android Calendar names for an intent service as a limit for the returned calendar events
	 * <br>2. List of Android Calendar names returned from our own CalendarService
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
	
	
	/**
	 * Name of a calendar event
	 */
	public static final String EVENT_NAME = "name";
	
	/**
	 * Name of the calendar in which the event is located
	 */
	public static final String EVENT_CALENDAR_NAME = "calendarName";
	
	/**
	 * Description of a calendar event
	 */
	public static final String EVENT_DESCRIPTION = "description";
	
	/**
	 * Location of a calendar event
	 */
	public static final String EVENT_LOCATION = "location";
	
	/**
	 * Start date of a calendar event
	 */
	public static final String EVENT_START_DATE = "startDate";
	
	/**
	 * End date of a calendar event
	 */
	public static final String EVENT_END_DATE = "endDate";
	
	/**
	 * Adds an event to the servers android calendar
	 */
	public static final String ADD_EVENT = "addEvent";
	
}
