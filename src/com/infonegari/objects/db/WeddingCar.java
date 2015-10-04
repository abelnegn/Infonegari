package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class WeddingCar extends SugarRecord<WeddingCar>{
	private long weddingCarId;
	private String weddingCarName;
	private long CarTypeId;
	private long LocationId;
	private float Price;
	private long MemberId;
	private String Discription;
	
    public boolean isNew() {
        long count = Select.from(WeddingCar.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getWeddingCarId() {
		return weddingCarId;
	}

	public void setWeddingCarId(long weddingCarId) {
		this.weddingCarId = weddingCarId;
	}

	public String getWeddingCarName() {
		return weddingCarName;
	}

	public void setWeddingCarName(String weddingCarName) {
		this.weddingCarName = weddingCarName;
	}

	public long getCarTypeId() {
		return CarTypeId;
	}

	public void setCarTypeId(long carTypeId) {
		CarTypeId = carTypeId;
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

	public long getMemberId() {
		return MemberId;
	}

	public void setMemberId(long memberId) {
		MemberId = memberId;
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
