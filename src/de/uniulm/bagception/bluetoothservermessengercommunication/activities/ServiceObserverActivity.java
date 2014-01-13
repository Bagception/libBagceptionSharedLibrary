package de.uniulm.bagception.bluetoothservermessengercommunication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import de.philipphock.android.lib.services.ServiceUtil;
import de.philipphock.android.lib.services.observation.ConstantFactory;
import de.philipphock.android.lib.services.observation.ServiceObservationActor;
import de.philipphock.android.lib.services.observation.ServiceObservationReactor;

public abstract class ServiceObserverActivity extends Activity
implements ServiceObservationReactor{
	/*with this, we can observe if the Service we're connecting to, 
	 * is running (here the service is PeripheryBluetoothService AKA (client) BluetoothMiddleware)
	to use this, we need to
	 * initialize it (onCreate)
	 * register it (onResume)
	 * unregister it (onPause)
	
	This class needs the CallbackInterface ServiceObservationReactor (this)
	*/
	private ServiceObservationActor soActor;
	

	//after we start the service, the service should answer, if he doesn't, we check that with this handler. No Response=not installed 
	private final Handler checkinstalledHandler = new Handler();

	/*HelperClass to connect with the service and communicate via Messages
	to use this, we need to:
	 * initialize it (onCreate)
	 * register it (onServiceStarted)
	 * unregister it (onPause and when we force stop the service: startStopService)
		
		This class needs the CallbackInterface MessengerHelperCallback (this)
		
		We communicate via Messages
		There are 4 types of messages: 
		 * StatusMessages = StatusInformation of the BLuetoothMiddleware 
		 * CommandMessages = Commands to be send to the BluetoothMiddleware
		 * ResponseMessages = if the BluetoothMiddleware is not sure what to do, it sends a Response, then any system connected via MessengerHelper can answer via ResponseAnswer 
		 * BundleMessages = Messages between 2 BluetoothDevices. The BluetoothMiddleware only forwards those messages!!!
		 
		 
		 To extend those Messages, edit BluetoothServerMessengerCommunication .constants
	*/
	private final Runnable serviceNotInstalledRunnable = new Runnable() {
		
		@Override
		public void run() {
			if (!isServiceStarted()){
				on_service_not_installed();
			}
			
		}
	};
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		soActor = new ServiceObservationActor(this,
				getServiceName()); //init
		
	}
	
	
	protected void on_service_start_stop_toggle_clicked(){
		Intent startStopService = new Intent(
				getServiceName());

		
		
		if (isServiceStarted()){
			//stopService();
		//	stopService(startStopService);
			stopService(startStopService);
		}else{
			//startService(startStopService);
			startService(startStopService);
			checkinstalledHandler.postDelayed(serviceNotInstalledRunnable, 100);
		}
	}
		
	
	protected abstract void on_service_not_installed();
	
	private boolean isServiceStarted(){
		return ServiceUtil.isServiceRunning(this, getServiceName());
	}
	
	protected abstract String getServiceName();

	
	
	
	// lifecycle methods

	@Override
	protected void onResume() {
		super.onResume();
		if (isServiceStarted()){
			onServiceStarted(getServiceName());
		}else{
			onServiceStopped(getServiceName()); 	
		}
		 
		soActor.register(this);
		Intent broadcastRequest = new Intent();

		//broadcast answer is handled by ServiceObservationReactor
		//with this, we foce the BluetoothMiddleware to resent if it is alive
		broadcastRequest
				.setAction(ConstantFactory 
						.getForceResendStatusString(getServiceName()));  
		sendBroadcast(broadcastRequest);
	}

	@Override
	protected void onPause() {
		soActor.unregister(this);
		super.onPause();
		

		
	}

	/**
	 * callback of ServiceObservationReactor
	 */
	@Override
	public void onServiceStarted(String serviceName) {
		
		onServiceStarted();
	}

	public void stopService(){
		Intent br = new Intent();
		br.setAction(ConstantFactory.getServiceShutdownString(getServiceName()));
		sendBroadcast(br);
		
	}

	protected abstract void onServiceStarted();
}
