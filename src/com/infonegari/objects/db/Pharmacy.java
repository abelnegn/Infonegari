package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Pharmacy extends SugarRecord<Pharmacy>{
	private long pharmacyId;
	private String Item_Name;
	private float Price;
	private long LocationId;
	private String Discription;
	private String Pharma_Type;
	
    public boolean isNew() {
        long count = Select.from(Pharmacy.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(long pharmacyId) {
		this.pharmacyId = pharmacyId;
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

	public String getPharma_Type() {
		return Pharma_Type;
	}

	public void setPharma_Type(String pharma_Type) {
		Pharma_Type = pharma_Type;
	}
    
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
