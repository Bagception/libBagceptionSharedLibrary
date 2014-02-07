package de.uniulm.bagception.services.attributes;

public class OurLocation {
	
	/**
	 * Maximal aberration of a locations position in m.
	 */
	public static final String LOCATION_ACCURACY_THRESHOLD = "locationAccuracyThreshold";
	
	
	
	/**
	 * Defines the type of a request. MUST be defined!
	 * <br>1. GETLOCATION for a location request
	 * <br>2. RESOLVEADDRESS resolves an address to lat long coordinates
	 */
	public static final String REQUEST_TYPE = "requestType";
	
	
	
	/**
	 * Requests a latitude and longitude position
	 */
	public static final String GETLOCATION = "getLocation";

	
	
	/**
	 * Resolves an address to latitude longitude coordinates
	 */
	public static final String RESOLVEADDRESS = "resolveAddress";
	
	
	
	/**
	 * Resolves latitude and longitude coordinates to an address
	 */
	public static final String RESOLVECOORDS = "resolveCoords";
	
	/**
	 * Address which shall be resolved to latitude and longitude coordinates
	 */
	public static final String ADDRESS = "address";
	
	
	/**
	 * Defines the type of a response as a request reply. MUST be defined!
	 */
	public static final String RESPONSE_TYPE = "responseType";
	
	
	
	/**
	 * Defines the provider from which the request was resolved.
	 */
	public static final String PROVIDER = "provider";
	
	
	
	/**
	 * Latitude position from an address, location or current position.
	 */
	public static final String LATITUDE = "latitude";
	
	
	
	/**
	 * Longitude position from an address, location or current position.
	 */
	public static final String LONGITUDE = "longitude";
	
	
	
	/**
	 * Accuracy of a found position in m.
	 */
	public static final String ACCURACY = "accuracy";
	
	
	
	/**
	 * 
	 */
	public static final String LOCATION = "location";
	
	
	
	/**
	 * Request was resolved by the android network provider (cell).
	 */
	public static final String NETWORK = "network";
	
	
	
	/**
	 * Request was resolved by the android gps provider.
	 */
	public static final String GPS = "gps";
	
	
	
	/**
	 * Request was resolved by our own bluetooth provider.
	 */
	public static final String BLUETOOTH = "bluetooth";
	
	
	
	/**
	 * Request was resolved by our own wifi provider.
	 */
	public static final String WIFI = "wiwi";
	

	/**
	 * Request could not be resolved.
	 */
	public static final String NONE = "none";
	
	
	
	/**
	 * Defines the mac address of a resolved location request.
	 */
	public static final String MAC = "mac";
}
