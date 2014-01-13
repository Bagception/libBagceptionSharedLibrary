package de.uniulm.bagception.bundlemessageprotocol.entities;

import org.json.JSONException;
import org.json.JSONObject;

public class CalendarEvent {

	private String name;
	private String calendarName;
	private String description;
	private String location;
	private long startDate;
	private long endDate;
	
	public CalendarEvent(	String name, String calendarName, String description,
							String location, long startDate, long endDate){
		
		this.name = name;
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
	
	@Override
	public String toString() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("name", name);
			jsonObject.put("description", description);
			jsonObject.put("calendarName", calendarName);
			jsonObject.put("location", location);
			jsonObject.put("startDate", startDate);
			jsonObject.put("endDate", endDate);
			return jsonObject.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static CalendarEvent fromJSON(JSONObject jsonObject){
		CalendarEvent event = null;
		try {
			String name = (String) jsonObject.get("name");
			String calendarName = jsonObject.getString("calendarName");
			String description = jsonObject.getString("description");
			String location = jsonObject.getString("location");
			long startDate = jsonObject.getLong("startDate");
			long endDate = jsonObject.getLong("endDate");
			return new CalendarEvent(name, calendarName, description, location, startDate, endDate);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return event;
	}
}
