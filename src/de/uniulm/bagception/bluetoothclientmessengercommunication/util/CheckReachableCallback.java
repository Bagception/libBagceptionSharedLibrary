package de.uniulm.bagception.bluetoothclientmessengercommunication.util;

import android.bluetooth.BluetoothDevice;

public interface CheckReachableCallback {

	public void isReachable(BluetoothDevice device,boolean reachable);
	
}
