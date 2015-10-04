package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class ShopCategory extends SugarRecord<ShopCategory>{
	private long scId;
	private String Catagory_Name;
	
    public boolean isNew() {
        long count = Select.from(ShopCategory.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getScId() {
		return scId;
	}

	public void setScId(long scId) {
		this.scId = scId;
	}

	public String getCatagory_Name() {
		return Catagory_Name;
	}

	public void setCatagory_Name(String catagory_Name) {
		Catagory_Name = catagory_Name;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
