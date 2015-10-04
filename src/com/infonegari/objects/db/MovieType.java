package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class MovieType extends SugarRecord<MovieType>{
	private long mtId;
	private String Movie_Type;
	
    public boolean isNew() {
        long count = Select.from(MovieType.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getMtId() {
		return mtId;
	}

	public void setMtId(long mtId) {
		this.mtId = mtId;
	}

	public String getMovie_Type() {
		return Movie_Type;
	}

	public void setMovie_Type(String movie_Type) {
		Movie_Type = movie_Type;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }   
}
