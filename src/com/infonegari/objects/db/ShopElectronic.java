package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class ShopElectronic extends SugarRecord<ShopElectronic>{
	private long seId;
	private String Item_Name;
	private float Price;
	private long LocationId;
	private String Discription;
	private String Brand_Name;
	private String Service_Type;
	private String Catagory;
	
    public boolean isNew() {
        long count = Select.from(ShopElectronic.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getSeId() {
		return seId;
	}

	public void setSeId(long seId) {
		this.seId = seId;
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

	public String getBrand_Name() {
		return Brand_Name;
	}

	public void setBrand_Name(String brand_Name) {
		Brand_Name = brand_Name;
	}

	public String getService_Type() {
		return Service_Type;
	}

	public void setService_Type(String service_Type) {
		Service_Type = service_Type;
	}

	public String getCatagory() {
		return Catagory;
	}

	public void setCatagory(String catagory) {
		Catagory = catagory;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
