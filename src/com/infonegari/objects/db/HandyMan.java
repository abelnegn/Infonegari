package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class HandyMan extends SugarRecord<HandyMan>{
	private long handyManId;
	private String HandyManName;
	private long LocationId;
	private long MemberId;
	private String HandyManProfession;
	
    public boolean isNew() {
        long count = Select.from(HandyMan.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getHandyManId() {
		return handyManId;
	}

	public void setHandyManId(long handyManId) {
		this.handyManId = handyManId;
	}



	public String getHandyManName() {
		return HandyManName;
	}

	public void setHandyManName(String handyManName) {
		HandyManName = handyManName;
	}

	public long getLocationId() {
		return LocationId;
	}

	public void setLocationId(long locationId) {
		LocationId = locationId;
	}

	public long getMemberId() {
		return MemberId;
	}

	public void setMemberId(long memberId) {
		MemberId = memberId;
	}

	public String getHandyManProfession() {
		return HandyManProfession;
	}

	public void setHandyManProfession(String handyManProfession) {
		HandyManProfession = handyManProfession;
	}
    
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
