package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class PhotoVideo extends SugarRecord<PhotoVideo>{
	private long PhotoVideoId;
	private String PhotoVideoName;
	private long LocationId;
	private float Price;
	private long MemberId;
	private String WorkType;
	private String Discription;
	
    public boolean isNew() {
        long count = Select.from(PhotoVideo.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getPhotoVideoId() {
		return PhotoVideoId;
	}


	public void setPhotoVideoId(long photoVideoId) {
		PhotoVideoId = photoVideoId;
	}


	public String getPhotoVideoName() {
		return PhotoVideoName;
	}


	public void setPhotoVideoName(String photoVideoName) {
		PhotoVideoName = photoVideoName;
	}


	public long getLocationId() {
		return LocationId;
	}


	public void setLocationId(long locationId) {
		LocationId = locationId;
	}


	public float getPrice() {
		return Price;
	}


	public void setPrice(float price) {
		Price = price;
	}


	public long getMemberId() {
		return MemberId;
	}


	public void setMemberId(long memberId) {
		MemberId = memberId;
	}


	public String getWorkType() {
		return WorkType;
	}


	public void setWorkType(String workType) {
		WorkType = workType;
	}

	
	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
