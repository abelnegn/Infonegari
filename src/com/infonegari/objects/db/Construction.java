package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Construction extends SugarRecord<Construction> {
	private long ConstructionSnSId;
	private String ConstructionTitle;
	private long LocationId;
	private String Profession;
	private long ConstructionMachineId;
	private long ConstructionMaterialId;
	private String Price;
	private String Discription;
	private String User_Name;

    public boolean isNew() {
        long count = Select.from(Construction.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getConstructionSnSId() {
		return ConstructionSnSId;
	}

	public void setConstructionSnSId(long constructionSnSId) {
		ConstructionSnSId = constructionSnSId;
	}

	public String getConstructionTitle() {
		return ConstructionTitle;
	}

	public void setConstructionTitle(String constructionTitle) {
		ConstructionTitle = constructionTitle;
	}

	public long getLocationId() {
		return LocationId;
	}

	public void setLocationId(long locationId) {
		LocationId = locationId;
	}

	public String getProfession() {
		return Profession;
	}

	public void setProfession(String profession) {
		Profession = profession;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public long getConstructionMachineId() {
		return ConstructionMachineId;
	}

	public void setConstructionMachineId(long constructionMachineId) {
		ConstructionMachineId = constructionMachineId;
	}

	public long getConstructionMaterialId() {
		return ConstructionMaterialId;
	}

	public void setConstructionMaterialId(long constructionMaterialId) {
		ConstructionMaterialId = constructionMaterialId;
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
