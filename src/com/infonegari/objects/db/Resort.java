package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Resort extends SugarRecord<Resort>{
	private long resortId;
	private String Item_Name;
	private float Price;
	private long LocationId;
	private String Discription;
	
    public boolean isNew() {
        long count = Select.from(Resort.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getResortId() {
		return resortId;
	}

	public void setResortId(long resortId) {
		this.resortId = resortId;
	}

	public String getItem_Name() {
		return Item_Name;
	}

	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
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
