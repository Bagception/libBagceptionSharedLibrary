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

	public boolean equals(Object o) {
		if (o==null) return false;
		if (this == o) return true;
		Entity oe = (Entity) o;
		if (id != oe.getId()) return false;
		if (name != null){
			if (!name.equals(oe.name)) return false;	
		}else{
			if (oe.name != null) return false;
		}
		
		return true;
	}
	
}
