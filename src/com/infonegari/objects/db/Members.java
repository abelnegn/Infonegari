package com.infonegari.objects.db;

import java.util.Date;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Members extends SugarRecord<Members>{
	private long MemberId;
	private String FirstName;
	private String LastName;
	private String BusinessName;
	private String PhoneNo;
	private Date dateJoined;
	
   public boolean isNew() {
	   long count = Select.from(Members.class).where(Condition.prop("id").eq(id)).count();
    	return count == 0;
   }

	
	public long getMemberId() {
	return MemberId;
}


public void setMemberId(long memberId) {
	MemberId = memberId;
}


	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getBusinessName() {
		return BusinessName;
	}
	
	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}
	
	public String getPhoneNo() {
		return PhoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	
	public Date getDateJoined() {
		return dateJoined;
	}
	
	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
