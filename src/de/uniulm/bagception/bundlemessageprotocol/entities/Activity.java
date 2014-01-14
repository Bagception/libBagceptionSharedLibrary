package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import de.uniulm.bagception.bundlemessageprotocol.serializer.ItemListSerializer;

public class Activity {

	private final List<Item> itemsForActivity;
	private final String name;
	
	public Activity(String name,List<Item> itemsForActivity) {
		this.name = name;
		this.itemsForActivity = itemsForActivity;
	}
	
	
	//get
	
	public String getName() {
		return name;
	}
	
	public List<Item> getItemsForActivity() {
		return itemsForActivity;
	}
	
	//Serializing
	
	@Override
	public String toString() {
		JSONObject ret = new JSONObject();
		try {
			ret.put("name", name);
			ret.put("items", ItemListSerializer.serialize(itemsForActivity));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return ret.toString();
	}
	
	
	public static Activity fromJSON(JSONObject obj){
		String name;
		try {
			name = obj.getString("name");
			List<Item> items = ItemListSerializer.deserialize(obj.getJSONArray("items"));
			Activity ret = new Activity(name, items);
			return ret;
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return null;
	}
}
