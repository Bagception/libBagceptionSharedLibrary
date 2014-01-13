package de.uniulm.bagception.bluetoothclientmessengercommunication.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import de.uniulm.bagception.bluetoothclientmessengercommunication.BundleMessengerInterface;
import de.uniulm.bagception.broadcastconstants.BagceptionBroadcastContants;

public class BundleMessageHelper implements BundleMessengerInterface{

	private final Context context;
	public BundleMessageHelper(Context c) {
		context = c;
	}
	
	
	
	@Override
	public void sendMessageSendBundle(Bundle b) {
		sendBundleBroadcast(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_MESSAGE_SEND_INTENT, b);
	}
	
	@Override
	public void sendMessageRecvBundle(Bundle b) {
		sendBundleBroadcast(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_MESSAGE_RECV_INTENT, b);
	}


	@Override
	public void sendStatusBundle(Bundle b) {
		sendBundleBroadcast(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_STATUS_INTENT, b);
	}

	@Override
	public void sendResponseBundle(Bundle b) {
		sendBundleBroadcast(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_RESPONSE_INTENT, b);		
	}

	@Override
	public void sendCommandBundle(Bundle b) {
		sendBundleBroadcast(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_COMMAND_INTENT, b);
		
	}
	
	@Override
	public void sendResponseAnswerBundle(Bundle b) {
		sendBundleBroadcast(BagceptionBroadcastContants.BROADCAST_BUNDLEMESSAGE_RESPONSE_ANSWER_INTENT, b);
	}
	
	// //BundleMessengerInterface\\

	
	private void sendBundleBroadcast(String action,Bundle b){
		Intent toSend = new Intent(action);
		toSend.putExtras(b);
		LocalBroadcastManager.getInstance(context).sendBroadcast(toSend);
	
	}
}
