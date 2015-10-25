package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class WeddingHall extends SugarRecord<WeddingHall>{
	private long WeddingHallId;
	private String WeddingHallName;
	private String ServiceType;
	private String dateAvailable;
	private long LocationId;
	private float Price;
	private long MemberId;
	private String Discription;
	private String Hall_Type;
	private String Break_Fast;
	private String Lunch;
	private String Dinner;
	private String User_Name;
	
    public boolean isNew() {
        long count = Select.from(WeddingHall.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }
    

	public long getWeddingHallId() {
		return WeddingHallId;
	}


	public void setWeddingHallId(long weddingHallId) {
		WeddingHallId = weddingHallId;
	}


	public String getWeddingHallName() {
		return WeddingHallName;
	}
	public void setWeddingHallName(String weddingHallName) {
		WeddingHallName = weddingHallName;
	}
	public String getServiceType() {
		return ServiceType;
	}
	public void setServiceType(String serviceType) {
		ServiceType = serviceType;
	}

	public String getDateAvailable() {
		return dateAvailable;
	}


	public void setDateAvailable(String dateAvailable) {
		this.dateAvailable = dateAvailable;
	}


	public long getLocationId() {
		return LocationId;
	}
	public void setLocationId(long locationId) {
		LocationId = locationId;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public long getMemberId() {
		return MemberId;
	}
	public void setMemberId(long memberId) {
		MemberId = memberId;
	}

	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
	}

	public String getHall_Type() {
		return Hall_Type;
	}

	public void setHall_Type(String hall_Type) {
		Hall_Type = hall_Type;
	}

	public String getBreak_Fast() {
		return Break_Fast;
	}

	public void setBreak_Fast(String break_Fast) {
		Break_Fast = break_Fast;
	}

	public String getLunch() {
		return Lunch;
	}

	public void setLunch(String lunch) {
		Lunch = lunch;
	}

	public String getDinner() {
		return Dinner;
	}

	public void setDinner(String dinner) {
		Dinner = dinner;
	}

	public String getUser_Name() {
		return User_Name;
	}


	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
