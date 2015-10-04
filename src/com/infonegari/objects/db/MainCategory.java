package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class MainCategory extends SugarRecord<MainCategory>{
	private long CategoryId;
	private String CategoryName;
	
    public boolean isNew() {
        long count = Select.from(MainCategory.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getCategoryId() {
		return CategoryId;
	}


	public void setCategoryId(long categoryId) {
		CategoryId = categoryId;
	}


	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
    
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
