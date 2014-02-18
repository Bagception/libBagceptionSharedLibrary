package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class Entity {

	protected final long id;
	protected final String name;
	protected Entity(long id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public long getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	
	public String toString(){
		return toJSONObject().toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	protected JSONArray serializeArray(List<?> elements){
		JSONArray ret = new JSONArray();
		ret.addAll(elements);
		return ret;
	}
	

	
	public abstract JSONObject toJSONObject();
	
}
