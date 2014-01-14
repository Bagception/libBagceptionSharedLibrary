package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.uniulm.bagception.bundlemessageprotocol.serializer.ItemListSerializer;

public class ContainerStateUpdate {

	private final List<Item> itemList;
	private final Activity activity;
	public ContainerStateUpdate(Activity activity, List<Item> itemsInContainer) {
		this.itemList = itemsInContainer;
		this.activity = activity;
	}
	
	//get
	
	public List<Item> getItemList() {
		return itemList;
	}
	
	public Activity getActivity() {
		return activity;
	}
	
	
	//Serializing
	
	@Override
	public String toString() {
				
		return toJSONObject().toJSONString();
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject ret = new JSONObject();
		ret.put("activity", activity.toJSONObject());
		ret.put("itemList",ItemListSerializer.serialize(itemList));
		return ret;
	}
	public static ContainerStateUpdate fromJSON(JSONObject obj){
		
		try {
			JSONParser p = new JSONParser();
			JSONObject activityJSON = (JSONObject)p.parse(obj.get("activity").toString());
			Activity activity = Activity.fromJSON(activityJSON);
			p.reset();
			JSONArray arr;
			arr = (JSONArray)p.parse(obj.get("itemList").toString());
			List<Item> itemList = ItemListSerializer.deserialize(arr);
			ContainerStateUpdate ret = new ContainerStateUpdate(activity, itemList);
			return ret;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		

	}
}
