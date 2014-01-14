package de.uniulm.bagception.bundlemessageprotocol.serializer;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.uniulm.bagception.bundlemessageprotocol.entities.Item;

public class ItemListSerializer {

	@SuppressWarnings("unchecked")
	public static JSONArray serialize(List<Item> items){

		JSONArray ret = new JSONArray();
		ret.addAll(items);
		return ret;
	}
	
	public static List<Item> deserialize(JSONArray jsonArray){

		
		ArrayList<Item> l = new ArrayList<Item>();
		JSONParser p = new JSONParser();
		for (int i=0;i<jsonArray.size();i++){
			try {
				
				Object obj = p.parse(jsonArray.get(i).toString());
				JSONObject o = (JSONObject)obj;
				l.add(Item.fromJSON(o));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return l;
	}
	
}
