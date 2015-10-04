package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class AuctionCategory extends SugarRecord<AuctionCategory>{

	private long ac_id;
	private String Catagory_Name;
	
    public boolean isNew() {
        long count = Select.from(AuctionCategory.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }
	    
	public long getAc_id() {
		return ac_id;
	}


	public void setAc_id(long ac_id) {
		this.ac_id = ac_id;
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
