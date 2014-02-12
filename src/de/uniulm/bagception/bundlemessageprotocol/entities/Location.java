package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.simple.JSONObject;

public class Location {

	private final long id;
	private final String name;
	private final float lat;
	private final float lng;
	private final Integer radius;
	private final String mac;
	
	
	public Location(String name, String mac){
		id = -1;
		this.name = name;
		lat = (float) 0;
		lng = (float) 0;
		radius = -1;
		this.mac = mac;
	}
	
	public Location(String name, Float lat, Float lng, Integer radius){
		id = -1;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.radius = radius;
		mac = null;
	}
	
	public Location(String name, Float lat, Float lng, Integer radius, String mac){
		id = -1;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.radius = radius;
		this.mac = mac;
	}
	
	public Location(long id, String name, Float lat, Float lng, Integer radius, String mac){
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.radius = radius;
		this.mac = mac;
		this.id = id;
	}

	/**
	 * calculates if the given coordinates lie in this area 
	 * @param lat latitude of the target point
	 * @param lng longitude of the target point
	 * @return true if given point lies in this circle
	 */
	public boolean isIn(float lat, float lng) throws NoGPSPositionGivenException{
		 
		if (this.lat == 0 || this.lng == 0){
			throw new NoGPSPositionGivenException();
		}
		
		float rad = this.radius == null ? 1 : this.radius;
		
		//TODO calc
		
		return false;
	}
	
	
	
	public String getName() {
		return name;
	}

	public Float getLat() {
		return lat;
	}

	public Float getLng() {
		return lng;
	}

	public Integer getRadius() {
		return radius;
	}

	public String getMac() {
		return mac;
	}
	
	public long getId() {
		return id;
	}

	public class NoGPSPositionGivenException extends Exception{

		private static final long serialVersionUID = -7111254763949115778L;
		
	}

	
	//serialization
	
	public static Location fromJSON(JSONObject obj){
		String name = (String) obj.get("name");
		Float lng = Float.parseFloat(obj.get("lng").toString());
		Float lat = Float.parseFloat(obj.get("lat").toString());
		Integer rad = Integer.parseInt(obj.get("rad").toString());
		String mac = (String) obj.get("mac");
		Long id = Long.parseLong(obj.get("id").toString());
		return new Location(id,name, lat, lng, rad, mac);
	}
	
	@Override
	public String toString() {
		return toJSONObject().toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject ret = new JSONObject();
		ret.put("name", name);
		ret.put("lat", lat);
		ret.put("lng", lng);
		ret.put("rad", radius);
		ret.put("mac", mac);
		ret.put("id", id);
		return ret;
	}
}
