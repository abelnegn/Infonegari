package com.infonegari.objects.db;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class AddList extends SugarRecord<AddList>{

	private String title;
	private long LocationId;
	private String discription;
	private String category;
	private String itemType1;
	private String itemType2;
	private String itemType3;
	private String itemType4;
	private String userId;
	private String imageUrl;
	
    public boolean isNew() {
        long count = Select.from(AddList.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getLocationId() {
		return LocationId;
	}

	public void setLocationId(long locationId) {
		LocationId = locationId;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getItemType1() {
		return itemType1;
	}

	public void setItemType1(String itemType1) {
		this.itemType1 = itemType1;
	}

	public String getItemType2() {
		return itemType2;
	}

	public void setItemType2(String itemType2) {
		this.itemType2 = itemType2;
	}

	public String getItemType3() {
		return itemType3;
	}

	public void setItemType3(String itemType3) {
		this.itemType3 = itemType3;
	}

	public String getItemType4() {
		return itemType4;
	}

	public void setItemType4(String itemType4) {
		this.itemType4 = itemType4;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
       
}
