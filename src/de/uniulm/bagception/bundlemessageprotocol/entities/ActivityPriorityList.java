package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ActivityPriorityList extends Entity{

	private final List<Activity> activities;
	private final int[] priorities;
	
	public ActivityPriorityList(List<Activity> activityList,int[] priorityList) {
		super(-1,null);
		this.activities = activityList==null?new ArrayList<Activity>():activityList;
		this.priorities = priorityList==null?new int[0]:priorityList;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public int[] getPriorities() {
		return priorities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONObject() {
		JSONObject ret = new JSONObject();
		
		ret.put("activities", serializeArray(activities));
		JSONArray arr = new JSONArray();
		for(int i=0;i<priorities.length;i++){
			arr.add(priorities[i]);
		}
		ret.put("priorities", arr);
		return ret;
	}
	
	public static ActivityPriorityList fromJSON(JSONObject json){
		
		ArrayList<Activity> activities = deserializeActivities((JSONArray)json.get("activities"));
		
		
		JSONArray a = (JSONArray) json.get("priorities");
		int[] priorities = new int[a.size()];
		for(int i=0;i<a.size();i++){
			priorities[i]=Integer.parseInt(a.get(i).toString());			
		}
		return new ActivityPriorityList(activities,priorities);
	}
	
	public static ArrayList<Activity> deserializeActivities(JSONArray jsonArray){
		ArrayList<Activity> l = new ArrayList<Activity>();
		if (jsonArray == null) return l;
		
		JSONParser p = new JSONParser();
		for (int i=0;i<jsonArray.size();i++){
			try {
				
				Object obj = jsonArray.get(i);
				if (obj == null) continue;
				JSONObject o = (JSONObject) p.parse(obj.toString());
				
				
				if (o != null){
					l.add(Activity.fromJSON(o));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return l;
	}

	
	
}
