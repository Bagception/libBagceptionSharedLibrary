package de.uniulm.bagception.bundlemessageprotocol.entities.administration;

import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import de.uniulm.bagception.bundlemessageprotocol.entities.Activity;
import de.uniulm.bagception.bundlemessageprotocol.entities.Category;
import de.uniulm.bagception.bundlemessageprotocol.entities.Item;
import de.uniulm.bagception.bundlemessageprotocol.entities.Location;

public class AdministrationCommand<E> {

	
	public enum Operation {
		ADD, DEL, EDIT,LIST,START,STOP
	}
	
	public enum Entity{
		ACTIVITY, ITEM, LOCATION, CATEGORY;
	}
	
	public Operation operation; 

	private boolean isSuccessful;
	private String errorMessage;
	private Entity entity;
	private UUID streamId;
	private E[] payloadObjects;
	

	
	protected AdministrationCommand(Entity entity, Operation operation, UUID streamId, boolean isSuccessful,String errorMessage,E... objects) {
		this.operation = operation;
		this.isSuccessful = isSuccessful;
		this.errorMessage = errorMessage;
		this.streamId = streamId;
		this.entity = entity;
		this.payloadObjects = objects;
		
	}
	
	
	
	public UUID getStreamId() {
		return streamId;
	}


	public void setStreamId(UUID streamId) {
		this.streamId = streamId;
	}


	public E[] getPayloadObjects() {
		return payloadObjects;
	}


	public void setPayloadObjects(E[] payloadObjects) {
		this.payloadObjects = payloadObjects;
	}


	public Operation getOperation() {
		return operation;
	}


	public boolean isSuccessful() {
		return isSuccessful;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public Entity getEntity() {
		return entity;
	}



	
	//serialization
	
	
	@Override
	public String toString() {
		return toJSONObject().toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject ret = new JSONObject();
        
		ret.put("operation", operation.ordinal());
		ret.put("isSuccessful", isSuccessful);
		ret.put("errorMessage", errorMessage);
		ret.put("entity", entity.ordinal());
		ret.put("streamId", streamId.toString());
		JSONArray arr = new JSONArray();

		if (payloadObjects!=null){
			for(Object o: payloadObjects){
				arr.add(o);
			}
		}

		ret.put("payloadObjects", arr);
		
		return ret;
	}
	
	public static AdministrationCommand<?> fromJSONObject(JSONObject json){
		if (json == null) return null;
		Object jsonEntity = json.get("entity");
		int	ord = 0;
		if (jsonEntity != null){
			ord = Integer.parseInt(jsonEntity.toString());
		}else{
			return null;
		}
		
		Object jsonOp = json.get("operation");
		int	op = 0;
		if (jsonOp != null){
			op = Integer.parseInt(jsonOp.toString());
		}else{
			return null;
		}
		
		Entity e = Entity.values()[ord];
		Operation o = Operation.values()[op];
		
		
		boolean isSuccessful = Boolean.parseBoolean(json.get("isSuccessful").toString());
		
		String errorMessage = json.get("errorMessage").toString();
		UUID streamId = UUID.fromString(json.get("streamId").toString());
		JSONArray arr =(JSONArray) JSONValue.parse(json.get("payloadObjects").toString());
		
		
		switch (e) {
		case ACTIVITY:
		{
			Activity[] os = new Activity[arr.size()];
			for (int i = 0; i < os.length;i++){
				os[i] = Activity.fromJSON((JSONObject)JSONValue.parse(arr.get(i).toString()));
			}
			return new AdministrationCommand<Activity>(e,o,streamId,isSuccessful,errorMessage,os);
		}	
		case CATEGORY:
		{
			Category[] os = new Category[arr.size()];
			for (int i = 0; i < os.length;i++){
				os[i] = Category.fromJSON((JSONObject)JSONValue.parse(arr.get(i).toString()));
			}
			return new AdministrationCommand<Category>(e,o,streamId,isSuccessful,errorMessage,os);
		}
		case ITEM:
		{
			Item[] os = new Item[arr.size()];
			for (int i = 0; i < os.length;i++){
				os[i] = Item.fromJSON((JSONObject)JSONValue.parse(arr.get(i).toString()));
			}
			return new AdministrationCommand<Item>(e,o,streamId,isSuccessful,errorMessage,os);
		}	
		case LOCATION:
		{
			Location[] os = new Location[arr.size()];
			for (int i = 0; i < os.length;i++){
				os[i] = Location.fromJSON((JSONObject)JSONValue.parse(arr.get(i).toString()));
			}
			return new AdministrationCommand<Location>(e,o,streamId,isSuccessful,errorMessage,os);
		}				
		default:
			break;
		}
		return null;
	}
	
	
	
	//processing
	
	public void accept(AdministrationCommandProcessor p){
		p.process(this);
	}
}
