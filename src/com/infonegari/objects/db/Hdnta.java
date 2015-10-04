package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Hdnta extends SugarRecord<Hdnta>{
	private long HDnTAId;
	private String HDnTAName;
	private long LocationId;
	private long MemberId;
	private float Price;
	private String Discription;
	
    public boolean isNew() {
        long count = Select.from(Hdnta.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getHDnTAId() {
		return HDnTAId;
	}


	public void setHDnTAId(long hDnTAId) {
		HDnTAId = hDnTAId;
	}


	public String getHDnTAName() {
		return HDnTAName;
	}


	public void setHDnTAName(String hDnTAName) {
		HDnTAName = hDnTAName;
	}


	public long getLocationId() {
		return LocationId;
	}


	public void setLocationId(long locationId) {
		LocationId = locationId;
	}


	public long getMemberId() {
		return MemberId;
	}


	public void setMemberId(long memberId) {
		MemberId = memberId;
	}


	public float getPrice() {
		return Price;
	}


	public void setPrice(float price) {
		Price = price;
	}


	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
