package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class BeautySaloon extends SugarRecord<BeautySaloon>{
	private long beautysaloonsId;
	private String beautysaloonsName;
	private long LocationId;
	private String beautysaloonsType;
	private float Price;
	private long MemberId;
	private String Discription;
	private String User_Name;
	
    public boolean isNew() {
        long count = Select.from(BeautySaloon.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getBeautysaloonsId() {
		return beautysaloonsId;
	}

	public void setBeautysaloonsId(long beautysaloonsId) {
		this.beautysaloonsId = beautysaloonsId;
	}

	public String getBeautysaloonsName() {
		return beautysaloonsName;
	}


	public void setBeautysaloonsName(String beautysaloonsName) {
		this.beautysaloonsName = beautysaloonsName;
	}


	public long getLocationId() {
		return LocationId;
	}


	public void setLocationId(long locationId) {
		LocationId = locationId;
	}


	public String getBeautysaloonsType() {
		return beautysaloonsType;
	}


	public void setBeautysaloonsType(String beautysaloonsType) {
		this.beautysaloonsType = beautysaloonsType;
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
