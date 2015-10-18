package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class WeddingCardRingProtocol extends SugarRecord<WeddingCardRingProtocol>{
	private long wcrpId;
	private String WeddingCRPName;
	private long LocationId;
	private float Price;
	private String Discription;
	private long MemberId;
	
    public boolean isNew() {
        long count = Select.from(WeddingCardRingProtocol.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getWcrpId() {
		return wcrpId;
	}


	public void setWcrpId(long wcrpId) {
		this.wcrpId = wcrpId;
	}


	public String getWeddingCRPName() {
		return WeddingCRPName;
	}

	public void setWeddingCRPName(String weddingCRPName) {
		WeddingCRPName = weddingCRPName;
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

	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
	}

	public long getMemberId() {
		return MemberId;
	}

	public void setMemberId(long memberId) {
		MemberId = memberId;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
