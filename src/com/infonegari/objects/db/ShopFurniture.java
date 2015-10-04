package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class ShopFurniture extends SugarRecord<ShopFurniture>{
	private long sfId;
	private String Item_Name;
	private float Price;
	private long LocationId;
	private String Discription;
	private String Country;
	private String Item_Type;
	
    public boolean isNew() {
        long count = Select.from(ShopFurniture.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getSfId() {
		return sfId;
	}

	public void setSfId(long sfId) {
		this.sfId = sfId;
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

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getItem_Type() {
		return Item_Type;
	}

	public void setItem_Type(String item_Type) {
		Item_Type = item_Type;
	}
	
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
