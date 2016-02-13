package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Hdnta extends SugarRecord<Hdnta>{
	private long hdntaId;
	private String hdtaName;
	private long LocationId;
	private long MemberId;
	private String Price;
	private String Discription;
	private String User_Name;
	private String isFeatured;
	
    public boolean isNew() {
        long count = Select.from(Hdnta.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getHdntaId() {
		return hdntaId;
	}

	public void setHdntaId(long hdntaId) {
		this.hdntaId = hdntaId;
	}

	public String getHdtaName() {
		return hdtaName;
	}

	public void setHdtaName(String hdtaName) {
		this.hdtaName = hdtaName;
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

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
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

	public String getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(String isFeatured) {
		this.isFeatured = isFeatured;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}