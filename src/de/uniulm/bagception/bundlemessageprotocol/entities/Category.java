package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.simple.JSONObject;


public class Category {

	public static Category NO_CATEGORY = new Category(0, "uncategorized");
	
	public final int id;
	public final String name;
	
	public Category(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public Category(String name){
		this(-1,name);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static Category fromJSON(JSONObject json){
		int id = Integer.parseInt(json.get("id").toString());
		String name = (String) json.get("name");
		return new Category(id,name);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject ret = new JSONObject();
		ret.put("id", this.getId());
		ret.put("name", this.getName());
		return ret;
	}
	
	@Override
	public String toString() {
	
		return toJSONObject().toJSONString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o==null) return false;
		
		if (!(o instanceof Category)) {
			return false;
			
		}
		Category oCat = (Category) o;
		if (!this.getName().equals(oCat.getName())) return false;
		if (this.getId() != oCat.getId()) return false;
		
		return true;
	}
}
