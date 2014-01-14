package de.uniulm.bagception.bundlemessageprotocol.serializer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.uniulm.bagception.bundlemessageprotocol.entities.Item;

public class ItemListSerializer {

	public static JSONArray serialize(List<Item> items){
		JSONArray ret = new JSONArray();
		for (Item i:items){
			ret.put(i.toJSONObject());	
		}
		return ret;
		
	}
	
	public static List<Item> deserialize(JSONArray jsonArray){
		ArrayList<Item> ret = new ArrayList<Item>();
		try {
			
			
			JSONArray arr = new JSONArray(jsonArray);
			for (int i=0;i<arr.length();i++){
				Item item = Item.fromJSON(arr.getJSONObject(i));
				ret.add(item);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}
