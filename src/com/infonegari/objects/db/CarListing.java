package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class CarListing extends SugarRecord<CarListing>{
	private long CarListingId;
	private String CarName;
	private long CarTypeId;
	private String CarPrice;
	private String Discription;
	private long LocationId;
	private String Year;
	private long MemberId;
	private String isCarSale;
	private String User_Name;

    public boolean isNew() {
        long count = Select.from(CarListing.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }
    

	public long getCarListingId() {
		return CarListingId;
	}


	public void setCarListingId(long carListingId) {
		CarListingId = carListingId;
	}



	public String getCarName() {
		return CarName;
	}



	public void setCarName(String carName) {
		CarName = carName;
	}



	public long getCarTypeId() {
		return CarTypeId;
	}



	public void setCarTypeId(long carTypeId) {
		CarTypeId = carTypeId;
	}

	public String getCarPrice() {
		return CarPrice;
	}


	public void setCarPrice(String carPrice) {
		CarPrice = carPrice;
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

	public String getYear() {
		return Year;
	}


	public void setYear(String year) {
		Year = year;
	}


	public long getMemberId() {
		return MemberId;
	}



	public void setMemberId(long memberId) {
		MemberId = memberId;
	}


	public String getIsCarSale() {
		return isCarSale;
	}


	public void setIsCarSale(String isCarSale) {
		this.isCarSale = isCarSale;
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
