package com.infonegari.objects.db;

import java.util.Date;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class NewsLetter extends SugarRecord<NewsLetter>{
	private long NewsLetterId;
	private String GuestEmail;
	private Date RequestedDate;
	
    public boolean isNew() {
        long count = Select.from(NewsLetter.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getNewsLetterId() {
		return NewsLetterId;
	}


	public void setNewsLetterId(long newsLetterId) {
		NewsLetterId = newsLetterId;
	}


	public String getGuestEmail() {
		return GuestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		GuestEmail = guestEmail;
	}

	public Date getRequestedDate() {
		return RequestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		RequestedDate = requestedDate;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
