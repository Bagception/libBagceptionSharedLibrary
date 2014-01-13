package de.uniulm.bagception.bluetoothservermessengercommunication.service;

import android.os.Bundle;
import android.os.Message;
import de.philipphock.android.lib.services.messenger.MessengerService;
import de.uniulm.bagception.bluetoothservermessengercommunication.MessengerConstants;

public abstract class BundleMessengerService extends MessengerService{
	   public void sendMessageBundle(Bundle b){
		   Message m = Message.obtain(null,
					MessengerConstants.MESSAGE_BUNDLE_MESSAGE);
		   m.setData(b);
		   sendToClients(m);
		  
	   }
	   
	   public void sendStatusBundle(Bundle b){
		   Message m = Message.obtain(null,
					MessengerConstants.MESSAGE_BUNDLE_STATUS);
		   m.setData(b);
		   sendToClients(m);
		}
	   
	   public void sendResponseBundle(Bundle b){
		   Message m = Message.obtain(null,
					MessengerConstants.MESSAGE_BUNDLE_RESPONSE);
		   m.setData(b);
		   sendToClients(m);
		  
	   }
	   
	   public void sendCommandBundle(Bundle b){
		   Message m = Message.obtain(null,
					MessengerConstants.MESSAGE_BUNDLE_COMMAND);
		   m.setData(b);
		   sendToClients(m);
		  
	   }
}
