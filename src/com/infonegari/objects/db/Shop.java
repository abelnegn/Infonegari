package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Shop extends SugarRecord<Shop>{
	private long shopId;
	private String Item_Name;
	private long Catagory_Id;
	private String Discription;
	private long LocationId;
	private String Price;
	private String Color;
	private String Size;
	private String User_Name;
	
    public boolean isNew() {
        long count = Select.from(Shop.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getItem_Name() {
		return Item_Name;
	}

	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}

	public long getCatagory_Id() {
		return Catagory_Id;
	}

	public void setCatagory_Id(long catagory_Id) {
		Catagory_Id = catagory_Id;
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

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
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
