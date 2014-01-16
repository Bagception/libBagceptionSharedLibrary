package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item{

	private String name;
	private String description;
	private ArrayList<String> tagIDs;
	
	
	
	public Item(String name,String description,ArrayList<String> tagIDs){
		this.name=name;
		this.description=description;
		this.tagIDs=tagIDs;
	}
	public Item(String name){
		this(name,"");
	}
	public Item(String name,String description){
		this(name,description,new ArrayList<String>());
	}
	
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public List<String> getIds(){
		return tagIDs;
	}
	

	
	@Override
	/**
	 * converts the object to json
	 */
	public String toString() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("name", name);
			obj.put("description", description);
			
			JSONArray ar = new JSONArray();
			for (String id:tagIDs){
				ar.put(id);
			}
			obj.put("tagIDs", ar);
			return obj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static Item fromJSON(JSONObject obj) throws JSONException{
		String name = (String) obj.getString("name");
		String description = (String) obj.getString("description");
		JSONArray ar = obj.getJSONArray("tagIDs");
		ArrayList<String> tagIDs = new ArrayList<String>();
		for (int i=0;i<ar.length();i++){
			tagIDs.add(ar.getString(i));
		}
		return new Item(name,description,tagIDs);
		
	}
	
	
	
}
