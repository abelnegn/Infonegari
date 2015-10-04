package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Event extends SugarRecord<Event>{
	private long eventId;
	private String Item_Name;
	private String Discription;
	private float Price;
	private long LocationId;
	private String Event_Type;
	
    public boolean isNew() {
        long count = Select.from(Event.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    } 
	
	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getItem_Name() {
		return Item_Name;
	}

	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}

	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public long getLocationId() {
		return LocationId;
	}

	public void setLocationId(long locationId) {
		LocationId = locationId;
	}

	public String getEvent_Type() {
		return Event_Type;
	}

	public void setEvent_Type(String event_Type) {
		Event_Type = event_Type;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
