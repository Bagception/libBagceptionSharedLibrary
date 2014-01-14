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
		JSONObject ret = new JSONObject();
		try {
			ret.put("activity", activity.toString());
			ret.put("itemList",ItemListSerializer.serialize(itemList));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return ret.toString();
	}
	
	public static ContainerStateUpdate fromJSON(JSONObject obj){
		
		try {
			Activity activity = Activity.fromJSON(obj.getJSONObject("activity"));
			List<Item> itemList = ItemListSerializer.deserialize(obj.getJSONArray("itemList"));
			ContainerStateUpdate ret = new ContainerStateUpdate(activity, itemList);
			return ret;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
}
