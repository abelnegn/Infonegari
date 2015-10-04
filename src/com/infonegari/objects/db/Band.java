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
	private float Price;

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


	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
