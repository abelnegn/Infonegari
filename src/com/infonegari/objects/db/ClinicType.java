package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class ClinicType extends SugarRecord<ClinicType>{
	private long TypeId;
	private String Name;
	private String Name_am;
	
    public boolean isNew() {
        long count = Select.from(ClinicType.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getTypeId() {
		return TypeId;
	}


	public void setTypeId(long typeId) {
		TypeId = typeId;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getName_am() {
		return Name_am;
	}


	public void setName_am(String name_am) {
		Name_am = name_am;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
