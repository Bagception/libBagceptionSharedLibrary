package de.uniulm.bagception.bluetoothclientmessengercommunication;


import android.os.Bundle;

public interface BundleMessengerInterface {
	public void sendMessageRecvBundle(Bundle b);
	public void sendMessageSendBundle(Bundle b);

	public void sendStatusBundle(Bundle b);

	public void sendResponseBundle(Bundle b);
	public void sendResponseAnswerBundle(Bundle b);
	
	public void sendCommandBundle(Bundle b);
	
	
	
}
