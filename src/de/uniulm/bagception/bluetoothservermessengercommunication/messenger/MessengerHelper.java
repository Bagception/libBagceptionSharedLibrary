package de.uniulm.bagception.bluetoothservermessengercommunication.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import de.philipphock.android.lib.services.messenger.MessengerService;
import de.uniulm.bagception.bluetoothservermessengercommunication.MessengerConstants;

public class MessengerHelper {
	// communication with service
	private Messenger serviceMessenger;
	private boolean isConnectedWithService;

	private final MessengerHelperCallback callback;
	private final String serviceName;

	public MessengerHelper(MessengerHelperCallback callback, String serviceName) {
		this.callback = callback;
		this.serviceName = serviceName;
	}

	private final Handler incomingHandler = new Handler(new Handler.Callback() {

		// Handles incoming messages
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case MessengerConstants.MESSAGE_BUNDLE_MESSAGE:
				callback.onBundleMessage(msg.getData());
				break;

			case MessengerConstants.MESSAGE_BUNDLE_RESPONSE:
				callback.onResponseMessage(msg.getData());
				break;

			case MessengerConstants.MESSAGE_BUNDLE_STATUS:
				callback.onStatusMessage(msg.getData());
				break;
				
			case MessengerConstants.MESSAGE_BUNDLE_COMMAND:
				callback.onCommandMessage(msg.getData());
				break;
			}
			return false;
		}
	});

	// delivered to the server, handles incoming messages
	private final Messenger incomingMessenger = new Messenger(incomingHandler);

	private final ServiceConnection sconn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			serviceMessenger = null;
			isConnectedWithService = false;
			callback.disconnectedFromRemoteService();
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			serviceMessenger = new Messenger(service);
			isConnectedWithService = true;

			// We want to monitor the service for as long as we are
			// connected to it.
			try {
				Message msg = Message.obtain(null,
						MessengerService.MSG_REGISTER_CLIENT);
				msg.replyTo = incomingMessenger;
				serviceMessenger.send(msg);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			callback.connectedWithRemoteService();
		}
	};

	public void unregister(Context c) {
		if (isConnectedWithService) {
			// If we have received the service, and hence registered with
			// it, then now is the time to unregister.
			if (serviceMessenger != null) {
				try {
					Message msg = Message.obtain(null,
							MessengerService.MSG_UNREGISTER_CLIENT);
					msg.replyTo = incomingMessenger;
					serviceMessenger.send(msg);
				} catch (RemoteException e) {
					// There is nothing special we need to do if the service
					// has crashed.
				}
			}

			// Detach our existing connection.
			serviceMessenger = null;
			c.unbindService(sconn);
			isConnectedWithService = false;
			callback.disconnectedFromRemoteService();

		}
	}

	public void sendMessageBundle(Bundle b) {
		sendBundle(b, MessengerConstants.MESSAGE_BUNDLE_MESSAGE);
	}

	public void sendResponseBundle(Bundle b) {
		sendBundle(b, MessengerConstants.MESSAGE_BUNDLE_RESPONSE);
	}

	public void sendStatusBundle(Bundle b) {
		sendBundle(b, MessengerConstants.MESSAGE_BUNDLE_STATUS);
	}

	public void sendCommandBundle(Bundle b){
		sendBundle(b, MessengerConstants.MESSAGE_BUNDLE_COMMAND);
	}
	
	private void sendBundle(Bundle b, int messageType) {
		Message m = Message.obtain(null,
				messageType);
		m.setData(b);
		if (serviceMessenger == null) {
			callback.onError(new NullPointerException(
					"tried to send text but not connected with service"));
			return;
		}
		try {
			serviceMessenger.send(m);
		} catch (RemoteException e) {
			callback.onError(e);
		}
	}

	public void register(Context c) {
		Intent i = new Intent();
		i.setAction(serviceName);
		c.bindService(i, sconn, 0);
	}
}
