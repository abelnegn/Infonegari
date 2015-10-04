package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class UsedItem extends SugarRecord<UsedItem>{
	private long UsedItemId;
	private String UsedItemName;
	private long UsedItemTypeId;
	private long LocationId;
	private float Price;
	private long MemberId;
	
    public boolean isNew() {
        long count = Select.from(UsedItem.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }
	

	public long getUsedItemId() {
		return UsedItemId;
	}


	public void setUsedItemId(long usedItemId) {
		UsedItemId = usedItemId;
	}


	public String getUsedItemName() {
		return UsedItemName;
	}


	public void setUsedItemName(String usedItemName) {
		UsedItemName = usedItemName;
	}


	public long getUsedItemTypeId() {
		return UsedItemTypeId;
	}


	public void setUsedItemTypeId(long usedItemTypeId) {
		UsedItemTypeId = usedItemTypeId;
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


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
