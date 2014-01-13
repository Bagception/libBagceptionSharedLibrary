package de.uniulm.bagception.bluetoothclientmessengercommunication.actor;

import android.os.Bundle;
import de.philipphock.android.lib.Reactor;

public interface BundleMessageReactor extends Reactor{

	public void onBundleMessageRecv(Bundle b);
	public void onBundleMessageSend(Bundle b);
	public void onResponseMessage(Bundle b);
	public void onResponseAnswerMessage(Bundle b);
	public void onStatusMessage(Bundle b);
	public void onCommandMessage(Bundle b);
	
	public void onError(Exception e);
	
}
