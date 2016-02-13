package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class NewsLetter extends SugarRecord<NewsLetter>{
	private long NewsLetterId;
	private String title;
	private String detail;
	private String RequestedDate;
	
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

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getRequestedDate() {
		return RequestedDate;
	}


	public void setRequestedDate(String requestedDate) {
		RequestedDate = requestedDate;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
