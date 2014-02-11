package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.simple.JSONObject;

public class WeatherForecast {
	
	private String city;
	private float temp;
	private float wind;
	private float clouds;
	private float temp_min;
	private float temp_max;
	private float rain;
	
	public WeatherForecast(String city, float temp, float wind, float clouds, float temp_min, float temp_max, float rain){
		this.city = city;
		this.temp = temp;
		this.wind = wind;
		this.clouds = clouds;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.rain = rain;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public float getWind() {
		return wind;
	}

	public void setWind(float wind) {
		this.wind = wind;
	}

	public float getClouds() {
		return clouds;
	}

	public void setClouds(float clouds) {
		this.clouds = clouds;
	}

	public float getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(float temp_min) {
		this.temp_min = temp_min;
	}

	public float getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(float temp_max) {
		this.temp_max = temp_max;
	}

	public float getRain() {
		return rain;
	}

	public void setRain(float rain) {
		this.rain = rain;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("city", this.city);
		jsonObject.put("temp", this.temp);
		jsonObject.put("wind", this.wind);
		jsonObject.put("clouds", this.clouds);
		jsonObject.put("temp_min", this.temp_min);
		jsonObject.put("temp_max", this.temp_max);
		jsonObject.put("rain", this.rain);
		return jsonObject;
	}
	
	@Override
	public String toString() {
		return toJSONObject().toJSONString();
	}
	
	public static WeatherForecast fromJSON(JSONObject obj){
		String city = (String) obj.get("city");
		float temp  = (Float) obj.get("temp");
		float wind  = (Float) obj.get("wind");
		float clouds  = (Float) obj.get("clouds");
		float temp_min  = (Float) obj.get("temp_min");
		float temp_max  = (Float) obj.get("temp_max");
		float rain  = (Float) obj.get("rain");
		return new WeatherForecast(city, temp, wind, clouds, temp_min, temp_max, rain);
	}
	
//	private String city;
//	private float temp;
//	private float wind;
//	private float clouds;
//	private float temp_min;
//	private float temp_max;
//	private float rain;
	
	
	
	

}
