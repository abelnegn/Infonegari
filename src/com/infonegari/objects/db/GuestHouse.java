package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class GuestHouse extends SugarRecord<GuestHouse>{
	private long GuestHouseId;
	private String GuestHouseName;
	private String NoRooms;
	private long LocationId;
	private String Price;
	private String GuestHouseDiscripton;
	private String User_Name;
	private String isFeatured;

    public boolean isNew() {
        long count = Select.from(GuestHouse.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }
 

	public long getGuestHouseId() {
		return GuestHouseId;
	}


	public void setGuestHouseId(long guestHouseId) {
		GuestHouseId = guestHouseId;
	}


	public String getGuestHouseName() {
		return GuestHouseName;
	}

	public void setGuestHouseName(String guestHouseName) {
		GuestHouseName = guestHouseName;
	}

	public String getNoRooms() {
		return NoRooms;
	}

	public void setNoRooms(String noRooms) {
		NoRooms = noRooms;
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


	public String getGuestHouseDiscripton() {
		return GuestHouseDiscripton;
	}

	public void setGuestHouseDiscripton(String guestHouseDiscripton) {
		GuestHouseDiscripton = guestHouseDiscripton;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
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
