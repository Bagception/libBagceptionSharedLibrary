package de.uniulm.bagception.protocol.bundle;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;


public class BundleProtocol {
	private final BundleProtocolCallback callback;
	
	public BundleProtocol(final BundleProtocolCallback callback) {
		this.callback=callback;
	}
	
	public void in(String s){
		try {
			JSONObject json = new JSONObject(s);
			
			Bundle b = new Bundle();
			
			for(@SuppressWarnings("unchecked")
			Iterator<String> jsonKeysIterator = json.keys();jsonKeysIterator.hasNext();){
				String key = jsonKeysIterator.next();
				b.putString(key, json.getString(key));
			}
			callback.onBundleRecv(b);
		} catch (JSONException e) {
			//TODO handle
			e.printStackTrace();
		}

//		Parcel p = Parcel.obtain();
//		byte[] strBytes = s.getBytes();
//		p.unmarshall(strBytes, 0, strBytes.length);
//		Bundle b=new Bundle();
//		b.readFromParcel(p);
//		callback.onBundleRecv(b);
		
	}
	
	public String out(Bundle b){
		JSONObject json = new JSONObject();
		for(String key:b.keySet()){
			try {
				json.put(key, b.getString(key));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return json.toString();
		
//		Parcel parcel = Parcel.obtain();
//		b.writeToParcel(parcel, 0);
//		return parcel.marshall().toString();
		

	}
}
