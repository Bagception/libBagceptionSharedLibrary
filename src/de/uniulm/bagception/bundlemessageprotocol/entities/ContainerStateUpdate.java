package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

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
				
		return getJSONObject().toString();
	}
	public JSONObject getJSONObject(){
		JSONObject ret = new JSONObject();
		try {
			ret.put("activity", activity.toJSONObject());
			ret.put("itemList",ItemListSerializer.serialize(itemList));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public static ContainerStateUpdate fromJSON(JSONObject obj){
		
		try {
			JSONObject activityJSON = obj.getJSONObject("activity");
			Activity activity = Activity.fromJSON(activityJSON);
			List<Item> itemList = ItemListSerializer.deserialize(obj.getJSONArray("itemList"));
			ContainerStateUpdate ret = new ContainerStateUpdate(activity, itemList);
			return ret;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
}
