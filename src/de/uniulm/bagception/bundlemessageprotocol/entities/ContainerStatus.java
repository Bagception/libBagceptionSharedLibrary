package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.JSONException;
import org.json.JSONObject;

public class ContainerStatus {

	public enum STATUS_CODE{
		NOT_A_CONTAINER_STATUS,RFID_SCAN_STARTED,RFID_SCAN,FINISHED;
	}
	
	private STATUS_CODE status;
	
	public ContainerStatus(STATUS_CODE status) {
		this.status = status;
	}

	public STATUS_CODE getStatus() {
		return status;
	}
	
	public static ContainerStatus fromJSON(JSONObject obj){
		int statuscode=0;
		try {
			statuscode = Integer.parseInt(obj.getString("status"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ContainerStatus(STATUS_CODE.values()[statuscode]);
	}
	
	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("status", getStatus().ordinal());
			return obj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			obj.put("status", 0);
		} catch (JSONException e) {	}
		
		return obj.toString();
	}
}
