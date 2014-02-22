package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.simple.JSONObject;

import android.util.Log;


public class CalendarEvent {

	private String name;
	private int eventId;
	private String calendarName;
	private String description;
	private String location;
	private long startDate;
	private long endDate;
	
	
	public CalendarEvent(	String name, int eventId, String calendarName, String description,
							String location, long startDate, long endDate){
		
		this.name = name;
		this.eventId = eventId;
		this.calendarName = calendarName;
		this.description = description;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getEventId(){
		return this.eventId;
	}
	
	public void setEventId(int eventId){
		this.eventId = eventId;
	}
	
	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(){
		JSONObject jsonObject = new JSONObject();
		
			jsonObject.put("name", this.name);
			jsonObject.put("eventId", this.eventId);
			jsonObject.put("description", this.description);
			jsonObject.put("calendarName", this.calendarName);
			jsonObject.put("location", this.location);
			jsonObject.put("startDate", this.startDate);
			jsonObject.put("endDate", this.endDate);
			return jsonObject;
		
	}
	
	@Override
	public String toString() {
		return toJSONObject().toJSONString();
	}
	
	public static CalendarEvent fromJSON(JSONObject jsonObject){
		String name = (String) jsonObject.get("name");
		int eventId = 1;
//		Integer.parseInt(jsonObject.get("eventId").toString());
		String calendarName = (String) jsonObject.get("calendarName");
		String description = (String) jsonObject.get("description");
		String location = (String) jsonObject.get("location");
		long startDate = (Long) jsonObject.get("startDate");
		long endDate = (Long) jsonObject.get("endDate");
		return new CalendarEvent(name, eventId, calendarName, description, location, startDate, endDate);
	}
}
