package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Location extends SugarRecord<Location>{
	private long LocationId;
	private long CityId;
	private String LocationName;
	
    public boolean isNew() {
        long count = Select.from(Location.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getLocationId() {
		return LocationId;
	}



	public void setLocationId(long locationId) {
		LocationId = locationId;
	}



	public long getCityId() {
		return CityId;
	}


	public void setCityId(long cityId) {
		CityId = cityId;
	}


	public String getLocationName() {
		return LocationName;
	}


	public void setLocationName(String locationName) {
		LocationName = locationName;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
