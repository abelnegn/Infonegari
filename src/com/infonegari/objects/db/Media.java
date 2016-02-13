package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Media extends SugarRecord<Media>{
	private long MediaId;
	private String MediaName;
	
    public boolean isNew() {
        long count = Select.from(Media.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getMediaId() {
		return MediaId;
	}

	public void setMediaId(long mediaId) {
		MediaId = mediaId;
	}

	public String getMediaName() {
		return MediaName;
	}

	public void setMediaName(String mediaName) {
		MediaName = mediaName;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
