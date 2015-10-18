package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class HouseListing extends SugarRecord<HouseListing>{
	private long HouseListingId;
	private String House_Name;
	private String HouseDiscription;
	private float HousePrice;
	private long HouseTypeId;
	private long LocationId;
	private int NoRooms;
	private String LotSize;
	private long MemberId;
	private boolean isSale;
	private boolean IsBusiness;
	
    public boolean isNew() {
        long count = Select.from(HouseListing.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getHouseListingId() {
		return HouseListingId;
	}


	public void setHouseListingId(long houseListingId) {
		HouseListingId = houseListingId;
	}

	public String getHouse_Name() {
		return House_Name;
	}


	public void setHouse_Name(String house_Name) {
		House_Name = house_Name;
	}


	public String getHouseDiscription() {
		return HouseDiscription;
	}

	public void setHouseDiscription(String houseDiscription) {
		HouseDiscription = houseDiscription;
	}

	public float getHousePrice() {
		return HousePrice;
	}

	public void setHousePrice(float housePrice) {
		HousePrice = housePrice;
	}

	public long getHouseTypeId() {
		return HouseTypeId;
	}

	public void setHouseTypeId(long houseTypeId) {
		HouseTypeId = houseTypeId;
	}

	public long getLocationId() {
		return LocationId;
	}

	public void setLocationId(long locationId) {
		LocationId = locationId;
	}

	public int getNoRooms() {
		return NoRooms;
	}

	public void setNoRooms(int noRooms) {
		NoRooms = noRooms;
	}

	public String getLotSize() {
		return LotSize;
	}

	public void setLotSize(String lotSize) {
		LotSize = lotSize;
	}

	public long getMemberId() {
		return MemberId;
	}

	public void setMemberId(long memberId) {
		MemberId = memberId;
	}

	public boolean isSale() {
		return isSale;
	}

	public void setSale(boolean isSale) {
		this.isSale = isSale;
	}

	public boolean isIsBusiness() {
		return IsBusiness;
	}

	public void setIsBusiness(boolean isBusiness) {
		IsBusiness = isBusiness;
	}
	
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
