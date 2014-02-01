package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.simple.JSONObject;

public class ItemAttribute {

	private final long item_id;
	private final String temperature;
	private final String weather;
	private final String lightness;
	
	
	public ItemAttribute(String temperature, String weather, String lightness) {
		item_id = -1;
		this.temperature = temperature;
		this.weather = weather;
		this.lightness = lightness;
	}
	
	public ItemAttribute(long item_id, String temperature, String weather, String lightness) {
		
		this.item_id = item_id;
		this.temperature = temperature;
		this.weather = weather;
		this.lightness = lightness;
	}
	
	
	// ---------------------------- getter ---------------------------- //
	
	public long getId(){
		return this.item_id;
	}
	
	public String getTemperature(){
		return this.temperature;
	}
	
	public String getWeather(){
		return this.weather;
	}
	
	public String getLightness(){
		return this.lightness;
	}
	
	
	
	
	public static ItemAttribute fromJSON(JSONObject json){
		long item_id = Integer.parseInt(json.get("item_id").toString());
		String temperature = (String) json.get("temperature");
		String weather = (String) json.get("weather");
		String lightness = (String) json.get("lightness");
		return new ItemAttribute(item_id, temperature, weather, lightness);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject ret = new JSONObject();
		ret.put("item_id", this.getId());
		ret.put("temperature", this.getTemperature());
		ret.put("weather", this.getWeather());
		ret.put("lightness", this.getLightness());
		return ret;
	}
}
