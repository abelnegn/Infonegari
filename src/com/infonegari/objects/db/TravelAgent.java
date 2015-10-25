package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class TravelAgent extends SugarRecord<TravelAgent>{
	private long taId;
	private String Item_Name;
	private String Discription;
	private float Price;
	private long LocationId;
	private String User_Name;
	
    public boolean isNew() {
        long count = Select.from(TravelAgent.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getTaId() {
		return taId;
	}

	public void setTaId(long taId) {
		this.taId = taId;
	}

	public String getItem_Name() {
		return Item_Name;
	}

	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
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

	public long getLocationId() {
		return LocationId;
	}

	public void setLocationId(long locationId) {
		LocationId = locationId;
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
