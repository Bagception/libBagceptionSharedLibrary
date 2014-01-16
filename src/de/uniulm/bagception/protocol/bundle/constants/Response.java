package de.uniulm.bagception.protocol.bundle.constants;

import de.uniulm.bagception.bluetoothservermessengercommunication.messenger.MessengerHelper;
import android.os.Bundle;
/**
 * 
 * when the system cannot decide what to do, a Response is sent out.
 * A ResponseReceiver is then used to decide what to do by sending a {@link ResponseAnswer} back.
 *  
 * @author phil
 *
 */
public enum Response {
	/**
	 * 
	 * if multiple devices are found, a response is sent out.
	 * As Extra-Data, the Payload @see {@link EXTRA_KEYS#PAYLOAD} contains a parcelableArrayList of BluetoothDevice
	 * 
	 * As response the payload should contain the device to be used
	 * 
	 * <b>keep in mind that any use of BluetoothDevice requires bluetooth permission</b> 
	 * 
	 */
	Ask_For_Specific_Device(0),
	
	/**
	 * if one device is found and the Middleware's settings demand a request in this case,
	 * this is used.
	 * As Extra-Data, the Payload @see {@link EXTRA_KEYS#PAYLOAD} contains a parcelable BluetoothDevice
	 * 
	 * <b>keep in mind that any use of BluetoothDevice requires bluetooth permission</b>
	 */
	Confirm_Established_Connection(1),
	
	/**
	 * clears all notifications, the user might see until the next poll
	 */
	CLEAR_RESPONSES(2),
	
	BLUETOOTH_CONNECTION(3);

	
	public static final String EXTRA_RESPONSECODE = "de.uniulm.bundle.extra.bluetooth.comm.responsecode";
	

	
	private final int responsecode;

	/*
	 * ############################################### ###############
	 * constructors ##############
	 * #################################################
	 */

	private Response(int code) {
		this.responsecode = code;
	}

	/*
	 * ############################################### ############### *
	 * getters/setters #############
	 * #################################################
	 */

	public int getResponseCode() {
		return responsecode;
	}
	
	/**
	 * creates a bundle ready to send to the remote endpoint
	 * Alternatively use the static method {@link #getResponseBundle(Bundle)}
	 * @return a bundle that can be sent via {@link MessengerHelper#sendResponseBundle(Bundle)}
	 */
	public Bundle toBundle(){
		return getResponseBundle(this);
	}
	
	/**
	 * HelperClass with String-Constants.
	 * All additional information is added after the Enum is created.
	 * The EXTRA_KEYS are the Strings used in the KEY part of bundle.putExtra
	 * @author phil
	 *
	 */
	public class EXTRA_KEYS{
		public static final String PAYLOAD = EXTRA_RESPONSECODE+"payload";  
		public static final String VALUE_HAS_CHANGED = EXTRA_RESPONSECODE+"valhaschanged";
		public static final String NOTIFICATIONS_TO_CLEAR = EXTRA_RESPONSECODE+"notificationstoclear";
	}

	/*
	 * ############################################### ############### statics
	 * ############# #################################################
	 */

	/**
	 * creates a bundle ready to send to the remote endpoint
	 * Alternatively use the static method {@link #toBundle(Bundle)}
	 * @return a bundle that can be sent via {@link MessengerHelper#sendResponseBundle(Bundle)}
	 */
	public static Bundle getResponseBundle(Response resp) {
		Bundle ret = new Bundle();
		ret.putInt(EXTRA_RESPONSECODE, resp.getResponseCode());
		return ret;
	}

	/**
	 * converts a bundle recv. from the remote endpoint 
	 * @param b the bundle
	 * @return the converted enum-representation
	 */
	public static Response getResponse(Bundle b) {
		return Response.values()[b.getInt(EXTRA_RESPONSECODE)];
	}

	

}

