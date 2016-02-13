package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class JobCategory extends SugarRecord<JobCategory>{
	private long jcId;
	private String Category_Name;
	private String Category_Name_am;
	
    public boolean isNew() {
        long count = Select.from(JobCategory.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getJcId() {
		return jcId;
	}

	public void setJcId(long jcId) {
		this.jcId = jcId;
	}

	public String getCategory_Name() {
		return Category_Name;
	}

	public void setCategory_Name(String category_Name) {
		Category_Name = category_Name;
	}
    
	public String getCategory_Name_am() {
		return Category_Name_am;
	}

	public void setCategory_Name_am(String category_Name_am) {
		Category_Name_am = category_Name_am;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
