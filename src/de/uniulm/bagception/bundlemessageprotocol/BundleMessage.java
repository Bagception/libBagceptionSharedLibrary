package de.uniulm.bagception.bundlemessageprotocol;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.os.Bundle;
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
		NOT_A_BUNDLE_MESSAGE,ITEM_FOUND,ITEM_NOT_FOUND,CONTAINER_STATUS, CONTAINER_STATUS_UPDATE,IMAGE_REQUEST,IMAGE_REPLY,CONTAINER_STATUS_UPDATE_REQUEST;
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
