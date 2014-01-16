package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import android.graphics.Bitmap;
import de.uniulm.bagception.bundlemessageprotocol.serializer.PictureSerializer;


public class Item extends Observable{

	private String name;
	private String description;
	private ArrayList<String> tagIDs;
	private Bitmap image;
	private int imageHash=-1;
	
	public int getImageHash() {
		return imageHash;
	}
	public void setImageHash(int imageHash) {
		this.imageHash = imageHash;
	}
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
	
	
	
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
		this.setChanged();
		this.notifyObservers();

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
		if (image!=null){
			int hash = PictureSerializer.serialize(image).hashCode();
			obj.put("image",hash);
		}else{
			obj.put("image","0");
		}
		
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
		int imageId = Integer.parseInt(obj.get("image").toString()); 
		Item i = new Item(name,description,ar);
		i.setImageHash(imageId);
		return i;
		
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
