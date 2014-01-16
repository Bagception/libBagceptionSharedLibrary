package de.uniulm.bagception.protocol.bundle.constants;

import android.os.Bundle;
import de.uniulm.bagception.bluetoothservermessengercommunication.messenger.MessengerHelper;
/**
 * Answer Message to Response @see {@link Response}
 * @author phil
 *
 */
public enum ResponseAnswer {
	
	Ask_For_Specific_Device(0), 
	
	Confirm_Established_Connection(1),
	
	NOT_AN_RESPONSE_ANSWER(2);
	
	
	private static final String EXTRA_RESPONSE_ANSWER_CODE = "de.uniulm.bundle.extra.bluetooth.comm.responsecode";
	
	
	
	private final int responseanswercode;

	/*
	 * ############################################### ###############
	 * constructors ##############
	 * #################################################
	 */

	private ResponseAnswer(int code) {
		this.responseanswercode = code;
	}

	/*
	 * ############################################### ############### *
	 * getters/setters #############
	 * #################################################
	 */

	public int getResponseAnswerCode() {
		return responseanswercode;
	}
	
	/**
	 * ACKnowledgement that the message is received by an endpoint<br><br>
	 * <b>To send an ACK-Message:</b><br>
	 * <pre>
	 * {@code
	 * MessengerHelper msgH; //should be initialized properly
	 * Bundle toSend = ResponseAnswer.Ask_For_Specific_Device.getACK();
	 * msgH.sendResponseBundle(toSend);
	 * }</pre>
	 * 
	 * @return the Bundle to send to a remote endpoint
	 */
	public Bundle getACK(){
		Bundle ret = toBundle();
		ret.putBoolean(ResponseAnswer.EXTRA_KEYS.isACK, true);
		return ret;
	}
	

	
	/**
	 * creates a bundle ready to send
	 * Alternatively use the static method {@link #getResponseAnswer(Bundle)}
	 * @return a bundle that can be sent via {@link MessengerHelper#sendResponseBundle(Bundle)}
	 */
	public Bundle toBundle(){
		return ResponseAnswer.getResponseAnswerBundle(this);
	}

	/*
	 * ############################################### ############### statics
	 * ############# #################################################
	 */

	
	
	/**
	 * creates a bundle ready to send
	 * Alternatively use the static method {@link #toBundle()}
	 * @return a bundle that can be sent via {@link MessengerHelper#sendResponseBundle(Bundle)}
	 */
	public static Bundle getResponseAnswerBundle(ResponseAnswer resp) {
		Bundle ret = new Bundle();
		ret.putInt(EXTRA_RESPONSE_ANSWER_CODE, resp.getResponseAnswerCode());
		return ret;
	}

	/**
	 * 
	 * @param b the bundle received from a remote endpoint
	 * @return enum representation of the bundle
	 */
	public static ResponseAnswer getResponseAnswer(Bundle b) {
		int code = b.getInt(EXTRA_RESPONSE_ANSWER_CODE,-1);
		
		if (code == -1){
			return NOT_AN_RESPONSE_ANSWER;
		}
			
		return ResponseAnswer.values()[b.getInt(EXTRA_RESPONSE_ANSWER_CODE)];
		
	}
	
	/**
	 * HelperClass with String-Constants.
	 * All additional information is added after the Enum is created.
	 * The EXTRA_KEYS are the Strings used in the KEY part of bundle.putExtra
	 * @author phil
	 *
	 */
	public class EXTRA_KEYS{
		public static final String PAYLOAD = EXTRA_RESPONSE_ANSWER_CODE+"payload";  
		public static final String isACK = EXTRA_RESPONSE_ANSWER_CODE+"isack";
	}
	
	/**
	 * Checks if the Bundle is an Acknowledgement-Message for a Response.
	 * <br>
	 * If an endpoint (that is usually the {@link ResponseSystem}) receives an <i>ACK</i>,
	 * this means that the Response was sucessfully read and processed 
	 * <br><br>
	 * <b>To receive an ACK-Message:</b>
	 * </br>
	 * <pre>
	 * {@code
	 * Bundle fromMessageHandler; //should be initialized properly
	 * boolean isAck = r.isACK(fromMessageHandler);
	 * 
	 * }</pre>
	 * 
	 * @param b the Bundle received by a handler
	 * @return true if the message is an ACK-Message
	 */
	public static boolean isACK(Bundle b){
		boolean ack = b.getBoolean(ResponseAnswer.EXTRA_KEYS.isACK, false);
		return ack;
		
	}
	


}

