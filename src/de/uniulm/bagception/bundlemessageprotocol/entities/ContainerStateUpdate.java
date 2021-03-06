package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.uniulm.bagception.bundlemessageprotocol.serializer.ItemListSerializer;

public class ContainerStateUpdate {

	private final List<Item> itemList;
	private final Activity activity;
	private final List<ContextSuggestion> suggestions;
	
	private final int batteryState;

	public ContainerStateUpdate(Activity activity, List<Item> itemsInContainer,List<ContextSuggestion> suggestions,
			int batteryState) {
		this.itemList = itemsInContainer;
		this.activity = activity;
		this.batteryState = batteryState;
		this.suggestions = suggestions;
	}

	// get

	public List<Item> getItemList() {
		return itemList;
	}

	public Activity getActivity() {
		return activity;
	}

	public int getBatteryState() {
		return batteryState;
	}
	public List<ContextSuggestion> getContextSuggestions(){
		return suggestions;
	}

	// Serializing

	@Override
	public String toString() {

		return toJSONObject().toJSONString();
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject() {
		JSONObject ret = new JSONObject();
		ret.put("activity", activity.toJSONObject());
		ret.put("batteryState", batteryState);
		ret.put("itemList", ItemListSerializer.serialize(itemList));
		
		if(suggestions != null){
			JSONArray a = new JSONArray();
			a.addAll(suggestions);
			ret.put("suggestions", a);
		}
		
		return ret;
	}

	public static ContainerStateUpdate fromJSON(JSONObject obj) {

		try {
			JSONParser p = new JSONParser();
			JSONObject activityJSON = (JSONObject) p.parse(obj.get("activity")
					.toString());
			Activity activity = Activity.fromJSON(activityJSON);
			p.reset();
			JSONArray arr;
			arr = (JSONArray) p.parse(obj.get("itemList").toString());
			List<Item> itemList = ItemListSerializer.deserialize(arr);
			int battery = Integer.parseInt(obj.get("batteryState").toString());
			
			List<ContextSuggestion> suggestions = new ArrayList<ContextSuggestion>();
			{
				JSONArray jsonArray = new JSONArray();
				if (obj.get("suggestions") != null){
					jsonArray=(JSONArray)obj.get("suggestions");
					
					
					for (int i=0;i<jsonArray.size();i++){
						try {
							
							Object obj2 = jsonArray.get(i);
							if (obj2 == null) continue;
							JSONObject o = (JSONObject) p.parse(obj2.toString());
							
							if (o != null){
								suggestions.add(ContextSuggestion.fromJSON(o));
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}	
				}
				
			}
			
			ContainerStateUpdate ret = new ContainerStateUpdate(activity,
					itemList, suggestions,battery);
			return ret;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Item> getMissingItems() {
		List<Item> itemsIn = this.getItemList();
		List<Item> itemsMustBeIn = this.getActivity().getItemsForActivity();
		ArrayList<Item> ret;
		if (itemsMustBeIn == null) {
			ret = new ArrayList<Item>();
		} else {
			ret = new ArrayList<Item>(itemsMustBeIn);
		}
		for (Item item : itemsIn) {
			//boolean b = ret.remove(item);
			ret.remove(item);
		}
		return ret;

	}

	public List<Item> getNeedlessItems() {
		List<Item> itemsIn = this.getItemList();
		List<Item> itemsMustBeIn = this.getActivity().getItemsForActivity();
		ArrayList<Item> mustIn;
		if (itemsMustBeIn == null) {
			mustIn = new ArrayList<Item>();
		} else {
			mustIn = new ArrayList<Item>(itemsMustBeIn);
		}

		ArrayList<Item> ret = new ArrayList<Item>();

		for (Item item : itemsIn) {
			boolean bb = mustIn.contains(item);

			if (bb == false) {
				ret.add(item);
			}

		}

		return ret;

	}


}
