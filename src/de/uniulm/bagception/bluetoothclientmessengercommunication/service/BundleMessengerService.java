package de.uniulm.bagception.bluetoothclientmessengercommunication.service;


import android.content.Intent;
import android.os.IBinder;
import de.philipphock.android.lib.services.observation.ObservableService;
import de.uniulm.bagception.bluetoothclientmessengercommunication.actor.BundleMessageActor;
import de.uniulm.bagception.bluetoothclientmessengercommunication.actor.BundleMessageReactor;

public abstract class BundleMessengerService extends ObservableService implements BundleMessageReactor{
	
	private BundleMessageActor bmActor;
	protected BundleMessageHelper bmHelper;
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		return super.onStartCommand(intent, flags, startId);
	}	
	@Override
	protected void onFirstInit() {
		bmHelper = new BundleMessageHelper(this);
		bmActor = new BundleMessageActor(this);
		bmActor.register(this);
	}

	@Override
	public void onDestroy() {
		bmActor.unregister(this);
		
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	
	
	
	
	
}
