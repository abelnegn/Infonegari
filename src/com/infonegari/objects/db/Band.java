package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Band extends SugarRecord<Band>{
	private long BandId;
	private String BandName;
	private long LocationId;
	private long MemberId;
	private String Discription;
	private String Price;
	private String User_Name;
	private String isFeatured;

    public boolean isNew() {
        long count = Select.from(Band.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getBandId() {
		return BandId;
	}

	public void setBandId(long bandId) {
		BandId = bandId;
	}



	public String getBandName() {
		return BandName;
	}

	public void setBandName(String bandName) {
		BandName = bandName;
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

	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
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
