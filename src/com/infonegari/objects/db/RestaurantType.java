package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class RestaurantType extends SugarRecord<RestaurantType>{
	private long RestaurantTypeId;
	private String RestaurantTypeName;

    public boolean isNew() {
        long count = Select.from(RestaurantType.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

    
	public long getRestaurantTypeId() {
		return RestaurantTypeId;
	}


	public void setRestaurantTypeId(long restaurantTypeId) {
		RestaurantTypeId = restaurantTypeId;
	}


	public String getRestaurantTypeName() {
		return RestaurantTypeName;
	}

	public void setRestaurantTypeName(String restaurantTypeName) {
		RestaurantTypeName = restaurantTypeName;
	}
    
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
