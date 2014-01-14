package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Item{

	private String name;
	private String description;
	private ArrayList<String> tagIDs;
	
	
	
	public Item(String name,String description,ArrayList<String> tagIDs){
		this.name=name;
		this.description=description;
		Collections.sort(tagIDs);
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
		return toJSONObject().toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("description", description);
		
		JSONArray ar = new JSONArray();
		for (String id:tagIDs){
			ar.add(id);
		}
		obj.put("tagIDs", ar);
		return obj;
	}
	
	public static Item fromJSON(JSONObject obj){
		String name = (String) obj.get("name");
		String description = (String) obj.get("description");
		@SuppressWarnings("unchecked")
		ArrayList<String> ar = (ArrayList<String>) obj.get("tagIDs");
		return new Item(name,description,ar);
		
	}
	
	@Override
	public int hashCode() {
		StringBuilder sb = new StringBuilder();
		for (String s:tagIDs){
			sb.append(s);
		}
		return sb.hashCode();
	}
	

	
}
