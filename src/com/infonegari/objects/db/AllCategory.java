package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class AllCategory extends SugarRecord<AllCategory>{
	
	private long acId;
	private String Table_Name;
	private String Category;
	private String Display;
	
    public boolean isNew() {
        long count = Select.from(AllCategory.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getAcId() {
		return acId;
	}

	public void setAcId(long acId) {
		this.acId = acId;
	}

	public String getTable_Name() {
		return Table_Name;
	}

	public void setTable_Name(String table_Name) {
		Table_Name = table_Name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getDisplay() {
		return Display;
	}

	public void setDisplay(String display) {
		Display = display;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
