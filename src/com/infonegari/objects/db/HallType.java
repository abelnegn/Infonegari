package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class HallType extends SugarRecord<HallType>{
	private long htId;
	private String Hall_Type;
	private String Hall_Type_am;
	
    public boolean isNew() {
        long count = Select.from(HallType.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getHtId() {
		return htId;
	}

	public void setHtId(long htId) {
		this.htId = htId;
	}

	public String getHall_Type() {
		return Hall_Type;
	}

	public void setHall_Type(String hall_Type) {
		Hall_Type = hall_Type;
	}

	public String getHall_Type_am() {
		return Hall_Type_am;
	}

	public void setHall_Type_am(String hall_Type_am) {
		Hall_Type_am = hall_Type_am;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
