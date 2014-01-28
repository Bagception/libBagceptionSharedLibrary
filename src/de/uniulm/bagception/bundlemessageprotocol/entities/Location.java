package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.simple.JSONObject;

public class Location {

	public int id;
	public String name;
	public Float lat;
	public Float lng;
	public Integer radius;
	public String mac;
	
	public Location(){
		
	}

	public Location(int id, String name, Float lat, Float lng,Integer radius,String mac){
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
		 
		if (this.lat == null || this.lng == 0){
			throw new NoGPSPositionGivenException();
		}
		
		float rad = this.radius == null ? 1 : this.radius;
		
		//TODO calc
		
		return false;
	}
	
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setLon(Float lon){
		this.lng = lon;
	}
	
	public void setLat(Float lat){
		this.lat = lat;
	}
	
	public void setRadius(int radius){
		this.radius = radius;
	}
	
	public void setMac(String mac){
		this.mac = mac;
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
	
	public int getId() {
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
		Integer id = Integer.parseInt(obj.get("id").toString());
		return new Location(id,name, lat, lng, rad, mac);
	}
	
	@Override
	public String toString() {
		return toJSONObject().toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject ret = new JSONObject();
		ret.put("lat", lat);
		ret.put("lng", lng);
		ret.put("rad", radius);
		ret.put("mac", mac);
		ret.put("id", id);
		return ret;
		
	}
}
