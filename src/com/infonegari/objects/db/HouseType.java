package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class HouseType extends SugarRecord<HouseType>{
	private long HouseTypeId;
	private String HouseTypeName;
	private String HouseTypeName_am;
	
    public boolean isNew() {
        long count = Select.from(HouseType.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getHouseTypeId() {
		return HouseTypeId;
	}


	public void setHouseTypeId(long houseTypeId) {
		HouseTypeId = houseTypeId;
	}


	public String getHouseTypeName() {
		return HouseTypeName;
	}

	public void setHouseTypeName(String houseTypeName) {
		HouseTypeName = houseTypeName;
	}

	public String getHouseTypeName_am() {
		return HouseTypeName_am;
	}


	public void setHouseTypeName_am(String houseTypeName_am) {
		HouseTypeName_am = houseTypeName_am;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
