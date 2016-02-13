package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class ElectronicsBrand extends SugarRecord<ElectronicsBrand>{
	private long ebId;
	private String Brand_Name;

    public boolean isNew() {
        long count = Select.from(ElectronicsBrand.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }
    
	public long getEbId() {
		return ebId;
	}


	public void setEbId(long ebId) {
		this.ebId = ebId;
	}


	public String getBrand_Name() {
		return Brand_Name;
	}


	public void setBrand_Name(String brand_Name) {
		Brand_Name = brand_Name;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
