package de.uniulm.bagception.protocol.bundle.constants;

import android.os.Bundle;

/**
 * StatusCode represents the Status of the BluetoothMiddleware.
 * @author phil
 *
 */
public enum StatusCode {


	
	DISCONNECTED(0),
	CONNECTED(1),
	ERROR(2),
	SCAN_DEVICES_DONE(3);
	
	private static final String EXTRA_STATUSCODE = "de.uniulm.bundle.extra.bluetooth.comm.statuscode";	


	private final int statuscode;
	

	
	/*###############################################
	###############     constructors   ##############
	#################################################*/
	
	private StatusCode(int code) {
		this.statuscode=code;
	}
	

	
	/*###############################################
	###############   getters/setters   #############
	#################################################*/

	public int getStatusCode(){
		return statuscode;
	}
	
	/**
	 * Creates a bundle that can be sent to the remote endpoint.
	 * Alternatively use the static method {@link #getStatusBundle(StatusCode)}
	 * @param statuscode
	 * @return Bundle that can be sent to the remote endpoint
	 */
	public Bundle toBundle(){
		return StatusCode.getStatusBundle(this);
	}
	
	

	/**
	 * HelperClass with String-Constants.
	 * All additional information is added after the Enum is created.
	 * The EXTRA_KEYS are the Strings used in the KEY part of bundle.putExtra
	 * @author phil
	 *
	 */
	public class EXTRA_KEYS{
		public static final String ERROR_MESSAGE = EXTRA_STATUSCODE+"error";
		public static final String CONNECTED_DEVICE_NAME = EXTRA_STATUSCODE+"connectedDeviceName";
	}
	
	

	
	/*###############################################
	###############       statics       #############
	#################################################*/

	/**
	 * Creates a bundle that can be sent to the remote endpoint.
	 * Alternatively use the non-static method {@link #toBundle()}.
	 * @param statuscode
	 * @return Bundle that can be sent to the remote endpoint
	 */
	public static Bundle getStatusBundle(StatusCode statuscode){
		Bundle ret = new Bundle();
		ret.putInt(EXTRA_STATUSCODE, statuscode.getStatusCode());
		
		return ret;
	}
	
	/**
	 * 
	 * @param b the bundle to be converted in a StatusCode
	 * @return the created StatusCode from b
	 */
	public static StatusCode getStatusCode(Bundle b){
		
		StatusCode ret = StatusCode.values()[b.getInt(EXTRA_STATUSCODE)];
		return ret;
	}
}
