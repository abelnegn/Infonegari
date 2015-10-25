package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Restaurant extends SugarRecord<Restaurant>{
	private long restaurant_id;
	private String Item_Name;
	private long LocationId;
	private long RestaurantTypeId;
	private String Discription;
	private long MemberId;
	private String User_Name;

    public boolean isNew() {
        long count = Select.from(Restaurant.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}


	public long getLocationId() {
		return LocationId;
	}

	public void setLocationId(long locationId) {
		LocationId = locationId;
	}

	public long getRestaurantTypeId() {
		return RestaurantTypeId;
	}

	public void setRestaurantTypeId(long restaurantTypeId) {
		RestaurantTypeId = restaurantTypeId;
	}

	public long getMemberId() {
		return MemberId;
	}

	public void setMemberId(long memberId) {
		MemberId = memberId;
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
