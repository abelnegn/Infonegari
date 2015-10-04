package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class UsedItemType extends SugarRecord<UsedItemType>{
	private long UsedItemTypeId;
	private String UsedItemTypeName;
	
    public boolean isNew() {
        long count = Select.from(UsedItemType.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getUsedItemTypeId() {
		return UsedItemTypeId;
	}

	public void setUsedItemTypeId(long usedItemTypeId) {
		UsedItemTypeId = usedItemTypeId;
	}




	public String getUsedItemTypeName() {
		return UsedItemTypeName;
	}


	public void setUsedItemTypeName(String usedItemTypeName) {
		UsedItemTypeName = usedItemTypeName;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
