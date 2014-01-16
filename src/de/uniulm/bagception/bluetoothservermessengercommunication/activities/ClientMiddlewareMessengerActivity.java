package de.uniulm.bagception.bluetoothservermessengercommunication.activities;

import de.uniulm.bagception.services.ServiceNames;

public abstract class ClientMiddlewareMessengerActivity extends ServiceObserverActivity{



	@Override
	protected void on_service_not_installed() {
		onMiddlewareNotInstalled();
	}

	protected abstract void onMiddlewareNotInstalled();
	
	@Override
	protected String getServiceName() {
		return ServiceNames.BLUETOOTH_CLIENT_SERVICE;
	}

	@Override
	public void onServiceStopped(String serviceName) {
		onMiddlewareStopped();
	}


	protected abstract void onMiddlewareStopped();

	
	@Override
	protected void onServiceStarted() {
		onMiddlewareStarted();
	
	}
	
	protected abstract void onMiddlewareStarted();
	
	

	

}
