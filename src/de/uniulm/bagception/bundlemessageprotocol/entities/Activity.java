package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.uniulm.bagception.bundlemessageprotocol.serializer.ItemListSerializer;

public class Activity {

	private final List<Item> itemsForActivity;
	private final String name;
	private final Location location;
	private final long id;
	
	
	public Activity(String name){
		this(name, new ArrayList<Item>());
	}
	

	public Activity(String name, Location location) {
		this(name, new ArrayList<Item>(), location);
	}
	
	public Activity(String name, List<Item> itemsForActivity) {
		this(name, itemsForActivity, null);
	}
	
	public Activity(String name,List<Item> itemsForActivity,Location location) {
		id = -1;
		this.name = name;
		this.itemsForActivity = itemsForActivity;
		this.location = location;
	}
	
	public Activity(long id, String name, List<Item> itemsForActivity, Location location) {
		this.id = id;
		this.name = name;
		this.itemsForActivity = itemsForActivity;
		this.location = location;
	}
	
	
	// get
	
	public long getId() {
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
		
		if (name == null){
			ret.put("name", null);
		}else{
			ret.put("name", name);
		}
		if (itemsForActivity == null){
			ret.put("items", null);
		}else{
			ret.put("items", ItemListSerializer.serialize(itemsForActivity));	
		}
		if (location == null){
			ret.put("location", null);
		}else{
			ret.put("location",location.toJSONObject());	
		}
		
		
		return ret;
	}
	
	public static Activity fromJSON(JSONObject obj){
		String name;
		JSONParser p = new JSONParser();
		List<Item> items;
		Location loc;
		try {
			{
				Object n = obj.get("name");
				if (n==null){
					name = "";
				}else{
					name = n.toString();
				}
			}
			
			{
				Object n = obj.get("items");
				JSONArray arr;
				
				if (n==null){
					items = null;
				}else{
					arr = (JSONArray)p.parse(n.toString());
					if (arr != null){
						items = ItemListSerializer.deserialize(arr);						
					}else{
						items = null;
					}
					
				}
			}
			
			{
				Object n = obj.get("location");
				JSONObject lOb;
				if (n==null){
					loc = null;
				}else{
					lOb = (JSONObject)p.parse(n.toString());
					if (lOb != null){
						loc = Location.fromJSON(lOb);						
					}else{
						loc = null;
					}
					
				}
			}
			
			
			Activity ret = new Activity(name, items,loc);
			return ret;
		
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return null;
	}
}
