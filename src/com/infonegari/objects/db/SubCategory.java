package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class SubCategory extends SugarRecord<SubCategory>{
	private long SubCategoryId;
	private String SubCategoryName;
	private long CategoryId;
	
    public boolean isNew() {
        long count = Select.from(SubCategory.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getSubCategoryId() {
		return SubCategoryId;
	}

	public void setSubCategoryId(long subCategoryId) {
		SubCategoryId = subCategoryId;
	}




	public String getSubCategoryName() {
		return SubCategoryName;
	}



	public void setSubCategoryName(String subCategoryName) {
		SubCategoryName = subCategoryName;
	}



	public long getCategoryId() {
		return CategoryId;
	}



	public void setCategoryId(long categoryId) {
		CategoryId = categoryId;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
