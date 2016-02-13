package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Ads extends SugarRecord<Ads>{

	private long adsId;
	private String Category;
	private String Image_mob;
	
    public boolean isNew() {
        long count = Select.from(Ads.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getAdsId() {
		return adsId;
	}

	public void setAdsId(long adsId) {
		this.adsId = adsId;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}
    
	public String getImage_mob() {
		return Image_mob;
	}

	public void setImage_mob(String image_mob) {
		Image_mob = image_mob;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
