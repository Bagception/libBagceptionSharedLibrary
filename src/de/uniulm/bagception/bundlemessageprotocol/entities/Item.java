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

	public static final String VISIBILITY_PUBLIC = "public";
	public static final String VISIBILITY_PRIVATE = "private";
	
	int id;
	String name;
	boolean isIndependentItem;
	boolean isActivityIndependent;
	Category category;
	String cat;
	
	private ArrayList<String> tagIDs;
	private Bitmap image;
	private int imageHash=-1;
	
	
	
	public Item() {
		
	}
	
	public Item(int id, String name, Category category, ArrayList<String> tagIDs, int imageHash, boolean isActivityIndependent, boolean isImportant){
		this.id = id;
		this.name=name;
		this.category = category;

		Collections.sort(tagIDs);
		this.tagIDs=tagIDs;
		this.imageHash = imageHash;
		this.isActivityIndependent = isActivityIndependent;
		this.isIndependentItem = isImportant;
		
		
//		if (! (Item.VISIBILITY_PRIVATE.equals(visibility) || Item.VISIBILITY_PUBLIC.equals(visibility))){
//		throw new IllegalArgumentException(String.format("Visibility must be a String value of {%s, %s}",Item.VISIBILITY_PRIVATE,Item.VISIBILITY_PUBLIC));
//		}
//		this.visibility = visibility;
		
	}
	

	
	//------------------------- setter -------------------------//
	
		public void setName(String name) {
			this.name = name;
		}
		
		public void setCategory(String category) {
			this.cat = category;
		}
	
		public void setImage(Bitmap image) {
			this.image = image;
			this.setChanged();
			this.notifyObservers();
	
		}
	
		public void setImageHash(int imageHash) {
			this.imageHash = imageHash;
		}
	
	//------------------------- getter -------------------------//
	
	
		public long getId() {
			return this.id;
		}
		
		public List<String> getIds(){
			return tagIDs;
		}
		
		public String getName() {
			return this.name;
		}
		
		public Category getCategory() {
			return this.category;
		}
		
		public boolean getIndependentItem() {
			return this.isIndependentItem;
		}
		
		public boolean getContextItem() {
			return this.isActivityIndependent;
		}
		
		public Bitmap getImage() {
			return image;
		}
		
		public int getImageHash() {
			return imageHash;
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
		obj.put("id", id);
		obj.put("name", name);
		Category cat = getCategory();
		if (cat==null){
			obj.put("category", null);	
		}else{
			obj.put("category", cat);
		}
		
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
		
		obj.put("isActivityIndependent", isActivityIndependent);
		obj.put("isImportant", isIndependentItem);
		                         
   

		return obj;
	}
	
	public static Item fromJSON(JSONObject obj){
		int id = Integer.parseInt(obj.get("id").toString());
		
		String name = (String) obj.get("name");
		@SuppressWarnings("unchecked")
		ArrayList<String> ar = (ArrayList<String>) obj.get("tagIDs");
		int imageId = Integer.parseInt(obj.get("image").toString());
		
		Category c = Category.fromJSON((JSONObject) obj.get("category"));
		//String visibility = (String) obj.get("visibility");
		boolean isImportant = (Boolean) obj.get("isImportant");
		boolean isActivityIndependent = (Boolean) obj.get("isActivityIndependent");
		
		Item i = new Item(id,name,c,ar,imageId,isActivityIndependent,isImportant);
		
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
	
	@Override
	public boolean equals(Object o) {
		if (o==null) return false;
		
		if (!(o instanceof Item)) {
			return false;
			
		}
		Item oItem = (Item) o;
		if (!getName().equals(oItem.getName())) return false;
		if (!getCategory().equals(oItem.getCategory())) return false;
		if (getImageHash() != oItem.getImageHash()) return false;
		if (getIds().size() != oItem.getIds().size()) return false;
		
		
		for(int i=0;i<getIds().size();i++){
			if (!getIds().get(i).equals(oItem.getIds().get(i))){
				return false;
			}
		}
		
		return true;
	} 
}
