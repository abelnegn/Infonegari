package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class FemaleCloth extends SugarRecord<FemaleCloth>{
	private long fcId;
	private String Item_Name;
	private String Discription;
	private long LocationId;
	private float Price;
	private String Color;
	private String Size;
	
    public boolean isNew() {
        long count = Select.from(FemaleCloth.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

    
	public long getFcId() {
		return fcId;
	}


	public void setFcId(long fcId) {
		this.fcId = fcId;
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


	public String getColor() {
		return Color;
	}


	public void setColor(String color) {
		Color = color;
	}


	public String getSize() {
		return Size;
	}


	public void setSize(String size) {
		Size = size;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
