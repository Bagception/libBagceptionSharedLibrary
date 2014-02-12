package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.simple.JSONObject;

import de.uniulm.bagception.services.attributes.OurLocation;


/**
 * Bundle protocol class for sending positioning information
 * @author xaffe
 *
 */
public class Position {

	private String provider;
	private float lat;
	private float lng;
	private float accuracy;
	
	public Position(String provider, float lat, float lng, float accuracy){
		this.provider = provider;
		this.lat = lat;
		this.lng = lng;
		this.accuracy = accuracy;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(OurLocation.PROVIDER, this.provider);
		jsonObject.put(OurLocation.LATITUDE, this.lat);
		jsonObject.put(OurLocation.LONGITUDE, this.lng);
		jsonObject.put(OurLocation.ACCURACY, this.accuracy);
		return jsonObject;
	}
	
	@Override
	public String toString() {
		return toJSONObject().toJSONString();
	}
	
	public static Position fromJSON(JSONObject obj){
		String provider = (String) obj.get(OurLocation.PROVIDER);
		float lat = (Float) obj.get(OurLocation.LATITUDE);
		float lng = (Float) obj.get(OurLocation.LONGITUDE);
		float accuracy = (Float) obj.get(OurLocation.ACCURACY);
		return new Position(provider, lat, lng, accuracy);
	}

}
