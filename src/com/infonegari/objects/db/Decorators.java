package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Decorators extends SugarRecord<Decorators>{
	private long DecoratorId;
	private String DecoratorName;
	private long LocationId;
	private float Price;
	private String Discription;
	private long MemberId;
	private String User_Name;
	
    public boolean isNew() {
        long count = Select.from(Decorators.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }
    

	public long getDecoratorId() {
		return DecoratorId;
	}


	public void setDecoratorId(long decoratorId) {
		DecoratorId = decoratorId;
	}


	public String getDecoratorName() {
		return DecoratorName;
	}


	public void setDecoratorName(String decoratorName) {
		DecoratorName = decoratorName;
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


	public String getDiscription() {
		return Discription;
	}


	public void setDiscription(String discription) {
		Discription = discription;
	}


	public long getMemberId() {
		return MemberId;
	}


	public void setMemberId(long memberId) {
		MemberId = memberId;
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
