package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.uniulm.bagception.bundlemessageprotocol.serializer.ItemListSerializer;

public class Activity {

	private List<Item> itemsForActivity;
	private String name;
	private Location location;
	private int id;
	
	public Activity(){
		
	}
	
	public Activity(String name,List<Item> itemsForActivity,Location location) {
		this.name = name;
		this.itemsForActivity = itemsForActivity;
		this.location = location;
	}
	
	public Activity(int id, String name, List<Item> itemsForActivity, Location location) {
		this.id = id;
		this.name = name;
		this.itemsForActivity = itemsForActivity;
		this.location = location;
	}
	
	// set
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	// get
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Item> getItemsForActivity() {
		return itemsForActivity;
	}
	
	public Location getLocation(){
		return location;
	}
	
	//Serializing
	
	@Override
	public String toString() {
		
		return toJSONObject().toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject ret = new JSONObject();
		ret.put("name", name);
		ret.put("items", ItemListSerializer.serialize(itemsForActivity));
		ret.put("location", location.toJSONObject());
		return ret;
	}
	
	public static Activity fromJSON(JSONObject obj){
		String name;
		JSONParser p = new JSONParser();
		try {
			name = obj.get("name").toString();
			
			JSONArray arr =(JSONArray)p.parse(obj.get("items").toString());
			List<Item> items = ItemListSerializer.deserialize(arr);
			JSONObject o = (JSONObject)p.parse(obj.get("location").toString());
			Location loc = Location.fromJSON(o);
			Activity ret = new Activity(name, items,loc);
			return ret;
		
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return null;
	}
}
