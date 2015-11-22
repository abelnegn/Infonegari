package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class CinemaPlace extends SugarRecord<CinemaPlace>{
	private long cp_id;
	private String Cinema_Name;
	private String Cinema_Name_am;
	
    public boolean isNew() {
        long count = Select.from(CinemaPlace.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getCp_id() {
		return cp_id;
	}

	public void setCp_id(long cp_id) {
		this.cp_id = cp_id;
	}


	public String getCinema_Name() {
		return Cinema_Name;
	}

	public void setCinema_Name(String cinema_Name) {
		Cinema_Name = cinema_Name;
	}

	public String getCinema_Name_am() {
		return Cinema_Name_am;
	}

	public void setCinema_Name_am(String cinema_Name_am) {
		Cinema_Name_am = cinema_Name_am;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
