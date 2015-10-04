package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class EducationCategory extends SugarRecord<EducationCategory>{
	private long ecId;
	private String Education_Level;
	
    public boolean isNew() {
        long count = Select.from(EducationCategory.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getEcId() {
		return ecId;
	}

	public void setEcId(long ecId) {
		this.ecId = ecId;
	}

	public String getEducation_Level() {
		return Education_Level;
	}

	public void setEducation_Level(String education_Level) {
		Education_Level = education_Level;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
