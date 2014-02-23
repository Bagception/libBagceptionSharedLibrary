package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.simple.JSONObject;

public class ItemAttribute {

	private final long id;
	private final long item_id;
	private final String temperature;
	private final String weather;
	private final String lightness;
	
	
	public ItemAttribute(String temperature, String weather, String lightness) {
		this(-1,-1,temperature,weather,lightness);
	}
	
	public ItemAttribute(long item_id, String temperature, String weather, String lightness) {
		this(-1,item_id,temperature,weather,lightness);
	}
	
	public ItemAttribute(long id, long item_id, String temperature, String weather, String lightness) {
		this.id = id;
		this.item_id = item_id;
		this.temperature = temperature == null ? "" : temperature;
		this.weather = weather == null ? "" : weather;
		this.lightness = lightness == null ? "" : lightness;
	}
	
	
	// ---------------------------- getter ---------------------------- //
	
	public long getId(){
		return this.id;
	}
	
	public long getItemId(){
		return this.item_id;
	}
	
	public String getTemperature(){
		return this.temperature;//warm, cold
	}
	
	public String getWeather(){
		return this.weather;//rainy, sunny
	}
	
	public String getLightness(){
		return this.lightness; //light, dark
	}
	
	@Override
	public String toString() {
		return toJSONObject().toJSONString();
	}
	
	
	public static ItemAttribute fromJSON(JSONObject json){
		long id = Integer.parseInt(json.get("id").toString());
		long item_id = Integer.parseInt(json.get("item_id").toString());
		String temperature = json.get("temperature").toString();
		String weather = json.get("weather").toString();
		String lightness = json.get("lightness").toString();
		return new ItemAttribute(id, item_id, temperature, weather, lightness);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject ret = new JSONObject();
		ret.put("id", this.getId());
		ret.put("item_id", this.getItemId());
		ret.put("temperature", this.getTemperature());
		ret.put("weather", this.getWeather());
		ret.put("lightness", this.getLightness());
		return ret;
	}
}
