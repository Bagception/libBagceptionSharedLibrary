package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;


public class Item extends Observable{

	private long id;
	private final String name;
	private final boolean isIndependentItem;
	private final boolean isActivityIndependent;
	private final Category category;
	private final ItemAttribute attributes;
	
	
	private final ArrayList<String> tagIDs;
	//private Bitmap image;
	private String imageString;
	private boolean serializeImage=false;
	
	
	
	public Item(String name){
		this(name, new ArrayList<String>());
	}
	
	public Item(String name, Category category) {
		this(name,category,new ArrayList<String>());
	}
	
	
	public Item(String name,ArrayList<String> ids){
		this(name,null,ids);
	}

	public Item(String name,String... ids){
		this(name,null,ids);
	}
	
	public Item(String name, Category category, ArrayList<String> tagIDs){
		this(-1, name, category, false, false, null,tagIDs);
	}
	
	public Item(String name, Category category, String... tagIDs){
		this(-1, name, category, false, false, null,tagIDs);
	}
	
	public Item(long id, String name, Category category, ArrayList<String> tagIDs){
		this(id, name, category, false, false, null, tagIDs);
	}
	
	public Item(long id, String name, Category category, String... tagIDs){
		this(id, name, category, false, false, null,tagIDs);
	}
		
	public Item(long id, String name, Category category, boolean isActivityIndependent, boolean isIndependentItem, ItemAttribute attributes) {
		this(id, name,category, isActivityIndependent, isIndependentItem,attributes,new ArrayList<String>());
	}
	
	public Item(long id, String name, Category category, boolean isActivityIndependent, boolean isIndependentItem, ItemAttribute attributes,final String... tagIDs) {
		this(id, name,category, isActivityIndependent, isIndependentItem,attributes,new ArrayList<String>(){
			private static final long serialVersionUID = 5211017474038101151L;

		{
			for(String s:tagIDs){
				add(s);
			}
		}});
		
	}

	
	public Item(long id, String name, Category category, boolean isActivityIndependent, boolean isIndependentItem, ItemAttribute attributes,ArrayList<String> tagIDs) {
		this.id = id;
		this.name=name;
		this.category = category;

		if (tagIDs!=null)
			Collections.sort(tagIDs);

		
		if (tagIDs == null){
			tagIDs = new ArrayList<String>();
		}
		this.tagIDs=tagIDs;
		this.isActivityIndependent = isActivityIndependent;
		this.isIndependentItem = isIndependentItem;
		this.attributes = attributes;
		
		
	}
	public void serializeImage(){
		serializeImage =  true;
	}
//	public Item(String name,ArrayList<String> tagIDs){
//		this(-1,name,null,Item.VISIBILITY_PUBLIC,tagIDs,0,false, false);
//	}

	
	public String getImageString(){
		return imageString;
	}
	
	
	public void setImageString(String imageString) {
        this.imageString = imageString;
        this.setChanged();
        this.notifyObservers();

	}
	
	public void setImage(Bitmap image){
		setImageString(serialize(image));
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
			if (imageString == null) return null;
			return deserialize(imageString);
		}
		
		public long getImageHash() {
			return id;
		}
		
		public ItemAttribute getAttribute() {
			return this.attributes;
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
		
		if (serializeImage && imageString != null){
			obj.put("serializedImage", imageString);
		}
			
		Category cat = getCategory();
		if (cat==null){
			obj.put("category", null);	
		}else{
			obj.put("category", cat);
		}
		
		
		long hash = id;
		obj.put("image",hash);
	
		JSONArray ar = new JSONArray();
		if (tagIDs != null){
			for (String id:tagIDs){
				ar.add(id);
			}
			obj.put("tagIDs", ar);
		}else{
			obj.put("tagIDs", null);
		}
		
		
		
		obj.put("isActivityIndependent", isActivityIndependent);
		obj.put("isImportant", isIndependentItem);
		                         
		ItemAttribute attribute = getAttribute();
		
		if(attribute == null){
			obj.put("attribute", null);
		} else{
			obj.put("attribute", attribute);
		}
   

		return obj;
	}
	
	public static Item fromJSON(JSONObject obj){
		long id = Long.parseLong(obj.get("id").toString());
		
		String name = (String) obj.get("name");
		@SuppressWarnings("unchecked")
		ArrayList<String> ar = (ArrayList<String>) obj.get("tagIDs");
		JSONObject catObjJson = (JSONObject) obj.get("category");
		Category c=null;
		if (catObjJson != null){
			c = Category.fromJSON(catObjJson);
		}
		boolean isImportant = (Boolean) obj.get("isImportant");
		boolean isActivityIndependent = (Boolean) obj.get("isActivityIndependent");
		
		JSONObject attrObjJson = (JSONObject) obj.get("attribute");
		ItemAttribute a = null;
		if(attrObjJson != null){
			a = ItemAttribute.fromJSON(attrObjJson);
		}
		
		
		String serializedImage = obj.get("serializedImage") != null ? obj.get("serializedImage").toString() : null;
		Item i = new Item(id,name,c,isActivityIndependent,isImportant,a,ar);
		if (serializedImage != null){
			i.setImageString(serializedImage);
		}else{
			i.setImageString(null);
		}
		
		
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
		if (name != null){
			if (!getName().equals(oItem.getName())) return false;
		}else{
			if (oItem.getName() != null){
				return false;
			}
		}
		
		if (category != null){
			if (!getCategory().equals(oItem.getCategory())) return false;
		}else{
			if (oItem.getCategory() != null){
				return false;
			}
		}
		
		if (getImageHash() != oItem.getImageHash()) return false;
		if (getIds().size() != oItem.getIds().size()) return false;
		
		if (getIds() != null){
			for(int i=0;i<getIds().size();i++){
				if (!getIds().get(i).equals(oItem.getIds().get(i))){
					return false;
				}
			}
		}else{
			if (oItem.getIds() != null) return false;
		}
		return true;
	} 
	
	public static String serialize(Bitmap picture){
	
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
		
		byte[] byteArray = stream.toByteArray();
		
		String enc=Base64.encodeToString(byteArray, Base64.DEFAULT);
		
		return enc;
	}
	
	public static Bitmap deserialize(String s){
		byte[] bytes = Base64.decode(s, Base64.DEFAULT);
		Bitmap bmp;
		
		bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return bmp;
	}
}
