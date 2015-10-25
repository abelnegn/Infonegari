package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class UserSite extends SugarRecord<UserSite>{

	private long usId;
	private String First_Name;
	private String Last_Name;
	private String E_mail;
	private String Phone_Number;
	private String Company_Name;
	private String User_Name;
	
    public boolean isNew() {
        long count = Select.from(UserSite.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getUsId() {
		return usId;
	}

	public void setUsId(long usId) {
		this.usId = usId;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getE_mail() {
		return E_mail;
	}

	public void setE_mail(String e_mail) {
		E_mail = e_mail;
	}

	public String getPhone_Number() {
		return Phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		Phone_Number = phone_Number;
	}

	public String getCompany_Name() {
		return Company_Name;
	}

	public void setCompany_Name(String company_Name) {
		Company_Name = company_Name;
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
