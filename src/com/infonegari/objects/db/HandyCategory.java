package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class HandyCategory extends SugarRecord<HandyCategory>{
	private long hcId;
	private String Category;
	private String Category_am;
	
    public boolean isNew() {
        long count = Select.from(HandyCategory.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getHcId() {
		return hcId;
	}


	public void setHcId(long hcId) {
		this.hcId = hcId;
	}


	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getCategory_am() {
		return Category_am;
	}

	public void setCategory_am(String category_am) {
		Category_am = category_am;
	}
    
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }  
}
