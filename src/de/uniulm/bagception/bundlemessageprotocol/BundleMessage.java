package de.uniulm.bagception.bundlemessageprotocol;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import de.uniulm.bagception.bundlemessageprotocol.entities.ContainerStatus;
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
		NOT_A_BUNDLE_MESSAGE,ITEM_FOUND,ITEM_NOT_FOUND,CONTAINER_STATUS;
	}
	
	//Item
	public Bundle toItemFoundBundle(Item item){
		
		return createBundle(BUNDLE_MESSAGE.ITEM_FOUND, item);
	}
	public Bundle toItemFoundNotBundle(Item item){
		
		return createBundle(BUNDLE_MESSAGE.ITEM_NOT_FOUND,item);
	}
	public Item toItemFound(Bundle b) throws JSONException{
		JSONObject json = new JSONObject(b.getString(PAYLOAD_EXTRA));
		return Item.fromJSON(json);
	}
	
	//container status
	public Bundle toContainerStatusBundle(ContainerStatus status){
		return createBundle(BUNDLE_MESSAGE.CONTAINER_STATUS, status);
	}
	public ContainerStatus toContainerStatus(Bundle b) throws JSONException{
		JSONObject json = new JSONObject(b.getString(PAYLOAD_EXTRA));
		return ContainerStatus.fromJSON(json);
	}
	
	
	
	private Bundle createBundle(BUNDLE_MESSAGE msg,Object payload){
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
