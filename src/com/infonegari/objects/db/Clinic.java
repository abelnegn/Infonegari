package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Clinic extends SugarRecord<Clinic>{
	private long clinicId;
	private String Item_Name;
	private float Price;
	private long LocationId;
	private String Discription;
	private String Clinic_Type;
	private String Job_Type;
	
    public boolean isNew() {
        long count = Select.from(Clinic.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }
    
	public long getClinicId() {
		return clinicId;
	}

	public void setClinicId(long clinicId) {
		this.clinicId = clinicId;
	}


	public String getItem_Name() {
		return Item_Name;
	}


	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}

	public float getPrice() {
		return Price;
	}


	public void setPrice(float price) {
		Price = price;
	}


	public long getLocationId() {
		return LocationId;
	}


	public void setLocationId(long locationId) {
		LocationId = locationId;
	}


	public String getDiscription() {
		return Discription;
	}


	public void setDiscription(String discription) {
		Discription = discription;
	}


	public String getClinic_Type() {
		return Clinic_Type;
	}


	public void setClinic_Type(String clinic_Type) {
		Clinic_Type = clinic_Type;
	}


	public String getJob_Type() {
		return Job_Type;
	}


	public void setJob_Type(String job_Type) {
		Job_Type = job_Type;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
