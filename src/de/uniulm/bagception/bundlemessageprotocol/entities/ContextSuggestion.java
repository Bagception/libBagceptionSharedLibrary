package de.uniulm.bagception.bundlemessageprotocol.entities;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.uniulm.bagception.bundlemessageprotocol.serializer.ItemListSerializer;

public class ContextSuggestion extends Entity{

	private final Item itemToReplace;
	public Item getItemToReplace() {
		return itemToReplace;
	}

	
	private final List<Item> replaceSuggestions;
	public List<Item> getReplaceSuggestions() {
		return replaceSuggestions;
	}
	
	private final CONTEXT reason;
	public CONTEXT getReason() {
		return reason;
	}
	
	
	public enum CONTEXT{WARM,COLD,RAIN,SUNNY,DARK,BRIGHT};
	
	public ContextSuggestion(Item itemToReplItem, List<Item> replaceSuggestions,CONTEXT reason) {
		super(-1,null);
		this.itemToReplace = itemToReplItem;
		this.replaceSuggestions= replaceSuggestions;
		this.reason=reason;
	}
	

	

	public static ContextSuggestion fromJSON(JSONObject json){
		
		try {
			Object o = json.get("itemToReplace");
			Item itemToReplItem = null;
			if (o!=null){
				itemToReplItem = Item.fromJSON((JSONObject) new JSONParser().parse(o.toString()));	
			}			
			List<Item> replaceSuggestions = ItemListSerializer.deserialize((JSONArray)json.get("replaceSuggestions")); 
			CONTEXT reason = CONTEXT.values()[Integer.parseInt(json.get("reason").toString())];
			return new ContextSuggestion(itemToReplItem, replaceSuggestions, reason);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONObject() {
		JSONObject ret = new JSONObject();
		ret.put("itemToReplace", itemToReplace==null?null:itemToReplace.toJSONObject());
		ret.put("replaceSuggestions",replaceSuggestions==null?null:ItemListSerializer.serialize(replaceSuggestions));
		ret.put("reason", reason.ordinal());
		return ret;
	}
	

	
	public static boolean isInItemsToReplace(List<ContextSuggestion> suggestions,Item item){
		for(ContextSuggestion sug:suggestions){
			if (sug.getItemToReplace().equals(item)){
				return true;
			}
		}
		return false;
	}
	public static boolean isInReplaceSuggestions(List<ContextSuggestion> suggestions,Item item){
		for(ContextSuggestion sug:suggestions){
			for(Item i:sug.getReplaceSuggestions()){
				if (i.equals(item)){
					return true;
				}
			}
			
		}
		return false;
	}



}