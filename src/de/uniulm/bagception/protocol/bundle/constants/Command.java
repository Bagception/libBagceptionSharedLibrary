package de.uniulm.bagception.protocol.bundle.constants;

import de.uniulm.bagception.bluetoothservermessengercommunication.messenger.MessengerHelper;
import android.os.Bundle;

public enum Command {
	TRIGGER_SCAN_DEVICES(0),PING(1),PONG(2),RESEND_STATUS(3),DISCONNECT(4),POLL_ALL_RESPONSES(5);

	private static final String EXTRA_COMMAND = "de.uniulm.bundle.extra.bluetooth.comm.command";

	private final int commandcode;

	/*
	 * ############################################### 
	 * ###############  constructors ##############
	 * #################################################
	 */

	private Command(int code) {
		this.commandcode = code;
	}

	/*
	 * ############################################### 
	 * ###############	 * getters/setters #############
	 * #################################################
	 */

	public int getCommandCode() {
		return commandcode;
	}

	/**
	 * creates a bundle ready to send to the remote endpoint
	 * Alternatively use the static method {@link #getCommandBundle(Bundle)}
	 * @return a bundle that can be sent via {@link MessengerHelper#sendCommandBundle(Bundle)}
	 */
	public Bundle toBundle() {
		return getCommandBundle(this);

	}
	
	/*
	 * ############################################### 
	 * ############### statics ############# 
	 * #################################################
	 */

	/**
	 * creates a bundle ready to send to the remote endpoint
	 * Alternatively use the static method {@link #toBundle(Bundle)}
	 * @return a bundle that can be sent via {@link MessengerHelper#sendCommandBundle(Bundle)}
	 */
	public static Bundle getCommandBundle(Command com) {
		Bundle ret = new Bundle();
		ret.putInt(EXTRA_COMMAND, com.getCommandCode());
		return ret;
	}
	
	/**
	 * converts a bundle recv. from the remote endpoint 
	 * @param b the bundle
	 * @return the converted enum-representation
	 */
	public static Command getCommand(Bundle b){
		Command ret = Command.values()[b.getInt(EXTRA_COMMAND)];
		
		return ret; 
	}
}
