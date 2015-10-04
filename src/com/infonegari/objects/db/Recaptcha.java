package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Recaptcha extends SugarRecord<Recaptcha>{
	private long recaptchaId;
	private String test;
	private String code;
	
    public boolean isNew() {
        long count = Select.from(Recaptcha.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getRecaptchaId() {
		return recaptchaId;
	}

	public void setRecaptchaId(long recaptchaId) {
		this.recaptchaId = recaptchaId;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
