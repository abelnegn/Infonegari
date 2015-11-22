package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class TenderCategory extends SugarRecord<TenderCategory>{
	private long tc_id;
	private String Catagory_Name;
	private String Catagory_Name_am;
	
    public boolean isNew() {
        long count = Select.from(TenderCategory.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getTc_id() {
		return tc_id;
	}

	public void setTc_id(long tc_id) {
		this.tc_id = tc_id;
	}

	public String getCatagory_Name() {
		return Catagory_Name;
	}

	public void setCatagory_Name(String catagory_Name) {
		Catagory_Name = catagory_Name;
	}
	
	public String getCatagory_Name_am() {
		return Catagory_Name_am;
	}

	public void setCatagory_Name_am(String catagory_Name_am) {
		Catagory_Name_am = catagory_Name_am;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
