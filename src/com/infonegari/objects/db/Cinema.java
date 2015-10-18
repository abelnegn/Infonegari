package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Cinema extends SugarRecord<Cinema>{
	private long CinemaId;
	private String CinemaTitle;
	private long CinemaPlaceId;
	private String ShowTime;
	private String ShowDate;
	private String Discription;
	private long Movie_Type;
	private float Price;
	private long LocationId;
	
    public boolean isNew() {
        long count = Select.from(Cinema.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getCinemaId() {
		return CinemaId;
	}



	public void setCinemaId(long cinemaId) {
		CinemaId = cinemaId;
	}



	public String getCinemaTitle() {
		return CinemaTitle;
	}


	public void setCinemaTitle(String cinemaTitle) {
		CinemaTitle = cinemaTitle;
	}


	public long getCinemaPlaceId() {
		return CinemaPlaceId;
	}


	public void setCinemaPlaceId(long cinemaPlaceId) {
		CinemaPlaceId = cinemaPlaceId;
	}


	public String getShowTime() {
		return ShowTime;
	}


	public void setShowTime(String showTime) {
		ShowTime = showTime;
	}
	

	public String getShowDate() {
		return ShowDate;
	}

	public void setShowDate(String showDate) {
		ShowDate = showDate;
	}

	public String getDiscription() {
		return Discription;
	}


	public void setDiscription(String discription) {
		Discription = discription;
	}


	public long getMovie_Type() {
		return Movie_Type;
	}

	public void setMovie_Type(long movie_Type) {
		Movie_Type = movie_Type;
	}

	public float getPrice() {
		return Price;
	}


	public void setPrice(float price) {
		Price = price;
	}


	public long getLocationId() {
		return LocationId;
	}


	public void setLocationId(long locationId) {
		LocationId = locationId;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
