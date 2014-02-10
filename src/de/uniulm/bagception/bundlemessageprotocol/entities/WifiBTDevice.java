package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.simple.JSONObject;

public class WifiBTDevice {
	
	private String name;
	private String mac;
	
	public WifiBTDevice(String name, String mac){
		this.name = name;
		this.mac = mac;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", this.name);
		jsonObject.put("mac", this.mac);
		return jsonObject;
	}
	
	@Override
	public String toString() {
		return toJSONObject().toJSONString();
	}
	
	public static WifiBTDevice fromJSON(JSONObject obj){
		String name = (String) obj.get("name");
		String mac = (String) obj.get("mac");
		return new WifiBTDevice(name, mac);
	}
}