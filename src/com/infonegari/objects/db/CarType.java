package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class CarType extends SugarRecord<CarType>{
	private long CarTypeId;
	private String CarTypeName;
	
    public boolean isNew() {
        long count = Select.from(CarType.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getCarTypeId() {
		return CarTypeId;
	}

	public void setCarTypeId(long carTypeId) {
		CarTypeId = carTypeId;
	}

	public String getCarTypeName() {
		return CarTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		CarTypeName = carTypeName;
	}
    

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
