package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class DJ extends SugarRecord<DJ>{
	private long DjId;
	private String DjName;
	private long LocationId;
	private float Price;
	private long MemberId;
	private String Discription;
	private String User_Name;

    public boolean isNew() {
        long count = Select.from(DJ.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getDjId() {
		return DjId;
	}


	public void setDjId(long djId) {
		DjId = djId;
	}


	public String getDjName() {
		return DjName;
	}

	public void setDjName(String djName) {
		DjName = djName;
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
