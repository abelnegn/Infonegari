package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class MovieTitle extends SugarRecord<MovieTitle>{
	private  long mtId;
	private String Movie_Title;
	
    public boolean isNew() {
        long count = Select.from(MovieTitle.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getMtId() {
		return mtId;
	}

	public void setMtId(long mtId) {
		this.mtId = mtId;
	}

	public String getMovie_Title() {
		return Movie_Title;
	}

	public void setMovie_Title(String movie_Title) {
		Movie_Title = movie_Title;
	}
	
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
