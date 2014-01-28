package de.uniulm.bagception.bundlemessageprotocol;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.os.Bundle;
import de.uniulm.bagception.bluetoothservermessengercommunication.messenger.MessengerHelper;
import de.uniulm.bagception.bundlemessageprotocol.entities.Item;

public class BundleMessage {

	private final String MESSAGE_TYPE_EXTRA="de.uniulm.bagception.bundle.messageprotocol.message_type";
	private final String PAYLOAD_EXTRA="payload";
	
	
	//singleton
	private static final BundleMessage instance = new BundleMessage();
	private BundleMessage(){}
	
	public static BundleMessage getInstance(){
		return instance;
	}
	
	public enum BUNDLE_MESSAGE{
		/**
		 * the received message is not a bundle message
		 */
		NOT_A_BUNDLE_MESSAGE,
		
		/**
		 * @deprecated this messagetype is no longer supported, use CONTAINER_STATUS_UPDATE instead
		 */
		ITEM_FOUND,
		
		/**
		 * if an item id is not found in the database 
		 */
		ITEM_NOT_FOUND,
		
		/**
		 * contains information about the container state, itself such as if the rfid reader state
		 * this feature is currently not implemented
		 */
		CONTAINER_STATUS, 
		
		/**
		 * container status updates are idempotent and contain a full snapshot about the system-state, such as items, activity
		 */
		CONTAINER_STATUS_UPDATE,
		
		/**
		 * caching mechanism, requests an image for a given hash
		 */
		IMAGE_REQUEST,
		
		/**
		 * caching mechanism, contains an image for a given hash
		 */
		IMAGE_REPLY,
		
		/**
		 * explicitly request a CONTAINER_STATUS_UPDATE
		 */
		CONTAINER_STATUS_UPDATE_REQUEST,
		
		/**
		 * this command is a superset of commands. A command is one of the Operation: {add, delete, edit, list} and has an Entity: {Activity, Item, Location, Category}
		 * <br>
		 * If an ADMINISTRATION_COMMAND comes from the client, it is request to do such an operation
		 * It the command comes from the server, it is the answer if the command was successfull or not. Command request/replys for one operation share the same stream-id  
		 */
		ADMINISTRATION_COMMAND;
	}
	
	//Item
	public Bundle toItemFoundBundle(Item item){
		
		return createBundle(BUNDLE_MESSAGE.ITEM_FOUND, item);
	}
	public Bundle toItemFoundNotBundle(Item item){
		
		return createBundle(BUNDLE_MESSAGE.ITEM_NOT_FOUND,item);
	}
	public Item toItemFound(Bundle b){
		
		return Item.fromJSON(extractObject(b));

		
	}
	

	public JSONObject extractObject(Bundle b){
		JSONParser p = new JSONParser();
		JSONObject o=null;
		try {
			o = (JSONObject)p.parse(b.getString(PAYLOAD_EXTRA));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return o;
		
	}
	
	
	
	public Bundle createBundle(BUNDLE_MESSAGE msg,Object payload){
		Bundle ret = new Bundle();
		
		ret.putString(MESSAGE_TYPE_EXTRA, msg.ordinal()+"");
		if (payload != null){
			ret.putString(PAYLOAD_EXTRA, payload.toString());
		}else{
			ret.putString(PAYLOAD_EXTRA, "");
		}
			
		return ret;
	}
	

	
	
	
	
	/**
	 * reads out the type-code and returns the appropriate {@link BUNDLE_MESSAGE} 
	 * @param b Bundle recv via Bluetooth
	 * @return the {@link BUNDLE_MESSAGE}, {@link BUNDLE_MESSAGE.NOT_A_BUNDLE_MESSAGE} if it's not a BundleMessage
	 */
	public BUNDLE_MESSAGE getBundleMessageType(Bundle b){
		int i=Integer.parseInt(b.getString(MESSAGE_TYPE_EXTRA,"0"));
		return BUNDLE_MESSAGE.values()[i];
	}
	
	
	
	
	
}
