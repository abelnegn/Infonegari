package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class PhotoVideo extends SugarRecord<PhotoVideo>{
	private long PhotoVideoId;
	private String PhotoVideoName;
	private long LocationId;
	private String Price;
	private long MemberId;
	private String WorkType;
	private String Discription;
	private String User_Name;
	private String isFeatured;
	
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


	public String getPrice() {
		return Price;
	}


	public void setPrice(String price) {
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

	public String getUser_Name() {
		return User_Name;
	}


	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}


	public String getIsFeatured() {
		return isFeatured;
	}


	public void setIsFeatured(String isFeatured) {
		this.isFeatured = isFeatured;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
