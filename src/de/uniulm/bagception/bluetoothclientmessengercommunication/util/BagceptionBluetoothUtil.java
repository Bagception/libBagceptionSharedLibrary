package de.uniulm.bagception.bluetoothclientmessengercommunication.util;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import de.uniulm.bagception.bluetoothclientmessengercommunication.BagceptionBTServiceInterface;

public class BagceptionBluetoothUtil {
	public static boolean isBagceptionServer(BluetoothDevice device){
		for (int i=0;i<device.getUuids().length;i++){
			UUID uuid = device.getUuids()[i].getUuid();
			if (uuid.toString().equalsIgnoreCase(BagceptionBTServiceInterface.BT_UUID)){
				return true;
			}
		}
		
		return false;
	}
	
	public static void checkReachable(final BluetoothDevice device,final UUID service,final CheckReachableCallback callback){
		BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
		Runnable reachableCheckAsync = new Runnable() {
			
			@Override
			public void run() {
				
				
				try {
					BluetoothSocket socket = device.createRfcommSocketToServiceRecord(service);
					socket.connect();
					socket.close();
				} catch (IOException e) {
					callback.isReachable(device, false);
					e.printStackTrace();
					return;
				}
				callback.isReachable(device, true);
			}
		};
		Thread t = new Thread(reachableCheckAsync);
		t.start();
	}
}
