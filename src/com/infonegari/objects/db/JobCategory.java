package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class JobCategory extends SugarRecord<JobCategory>{
	private long jcId;
	private String Category_Name;
	
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
    
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
