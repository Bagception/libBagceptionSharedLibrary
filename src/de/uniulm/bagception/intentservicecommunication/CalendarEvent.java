package de.uniulm.bagception.intentservicecommunication;

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
	
	
}
