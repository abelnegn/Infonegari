package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class City extends SugarRecord<City>{
	private long CityId;
	private String CityName;
	
    public boolean isNew() {
        long count = Select.from(City.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getCityId() {
		return CityId;
	}


	public void setCityId(long cityId) {
		CityId = cityId;
	}


	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
