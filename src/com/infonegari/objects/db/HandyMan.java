package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class HandyMan extends SugarRecord<HandyMan>{
	private long handyManId;
	private String Item_Name;
	private String Category;
	private long LocationId;
	private String User_Name;
	private String Discription;
	private String isFeatured;
	
    public boolean isNew() {
        long count = Select.from(HandyMan.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getHandyManId() {
		return handyManId;
	}

	public void setHandyManId(long handyManId) {
		this.handyManId = handyManId;
	}

	public String getItem_Name() {
		return Item_Name;
	}

	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
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

	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
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
