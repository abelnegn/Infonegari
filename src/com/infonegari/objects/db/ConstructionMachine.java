package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class ConstructionMachine extends SugarRecord<ConstructionMachine>{
	private long cmId;
	private String Machine;
	private String Machine_am;
	
    public boolean isNew() {
        long count = Select.from(ConstructionMachine.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getCmId() {
		return cmId;
	}

	public void setCmId(long cmId) {
		this.cmId = cmId;
	}

	public String getMachine() {
		return Machine;
	}

	public void setMachine(String machine) {
		Machine = machine;
	}
    
	public String getMachine_am() {
		return Machine_am;
	}

	public void setMachine_am(String machine_am) {
		Machine_am = machine_am;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
