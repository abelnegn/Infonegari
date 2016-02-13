package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class WeddingCloth extends SugarRecord<WeddingCloth>{
	private long WeddingClothId;
	private String WeddingClothName;
	private long LocationId;
	private String Price;
	private long MemberId;
	private String Discription;
	private String Cloth_Type;
	private String Service_Type;
	private String User_Name;
	private String isFeatured;
	
    public boolean isNew() {
        long count = Select.from(WeddingCloth.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getWeddingClothId() {
		return WeddingClothId;
	}


	public void setWeddingClothId(long weddingClothId) {
		WeddingClothId = weddingClothId;
	}


	public String getWeddingClothName() {
		return WeddingClothName;
	}

	public void setWeddingClothName(String weddingClothName) {
		WeddingClothName = weddingClothName;
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

	public String getCloth_Type() {
		return Cloth_Type;
	}

	public void setCloth_Type(String cloth_Type) {
		Cloth_Type = cloth_Type;
	}

	public String getService_Type() {
		return Service_Type;
	}

	public void setService_Type(String service_Type) {
		Service_Type = service_Type;
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
