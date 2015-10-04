package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class WeddingItemRent extends SugarRecord<WeddingItemRent>{
	private long WeddingItemId;
	private String WeddingItemName;
	private String WeddingItemDiscription;
	private float WeddingItemPrice;
	private long LocationId;
	private long MemberId;
	
    public boolean isNew() {
        long count = Select.from(WeddingItemRent.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getWeddingItemId() {
		return WeddingItemId;
	}

	public void setWeddingItemId(long weddingItemId) {
		WeddingItemId = weddingItemId;
	}

	public String getWeddingItemName() {
		return WeddingItemName;
	}

	public void setWeddingItemName(String weddingItemName) {
		WeddingItemName = weddingItemName;
	}

	public String getWeddingItemDiscription() {
		return WeddingItemDiscription;
	}

	public void setWeddingItemDiscription(String weddingItemDiscription) {
		WeddingItemDiscription = weddingItemDiscription;
	}

	public float getWeddingItemPrice() {
		return WeddingItemPrice;
	}

	public void setWeddingItemPrice(float weddingItemPrice) {
		WeddingItemPrice = weddingItemPrice;
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

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
