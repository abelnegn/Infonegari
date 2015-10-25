package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Cinema extends SugarRecord<Cinema>{
	private long CinemaId;
	private String CinemaTitle;
	private String Calendar;
	private String Discription;
	private long Movie_Type;
	private long LocationId;
	private String User_Name;
	
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

	public String getCalendar() {
		return Calendar;
	}

	public void setCalendar(String calendar) {
		Calendar = calendar;
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

	public long getLocationId() {
		return LocationId;
	}

	public void setLocationId(long locationId) {
		LocationId = locationId;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
