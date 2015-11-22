package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class EducationCategory extends SugarRecord<EducationCategory>{
	private long ecId;
	private String Education_Level;
	private String Education_Level_am;
	
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

	public String getEducation_Level_am() {
		return Education_Level_am;
	}

	public void setEducation_Level_am(String education_Level_am) {
		Education_Level_am = education_Level_am;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
