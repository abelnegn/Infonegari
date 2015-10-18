package com.infonegari.objects.db;

import java.util.Date;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Tender extends SugarRecord<Tender>{
	private long tender_id;
	private String Company_Name;
	private String Phone_Number;
	private String Post_Date;
	private String Submission_Deadline;
	private String Opening_Date;
	private String Tender_Catagory;
	private String Discription;
	private String Source;

    public boolean isNew() {
        long count = Select.from(Tender.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getTender_id() {
		return tender_id;
	}

	public void setTender_id(long tender_id) {
		this.tender_id = tender_id;
	}

	public String getCompany_Name() {
		return Company_Name;
	}

	public void setCompany_Name(String company_Name) {
		Company_Name = company_Name;
	}

	public String getPhone_Number() {
		return Phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		Phone_Number = phone_Number;
	}

	public String getPost_Date() {
		return Post_Date;
	}

	public void setPost_Date(String post_Date) {
		Post_Date = post_Date;
	}

	public String getSubmission_Deadline() {
		return Submission_Deadline;
	}

	public void setSubmission_Deadline(String submission_Deadline) {
		Submission_Deadline = submission_Deadline;
	}

	public String getOpening_Date() {
		return Opening_Date;
	}

	public void setOpening_Date(String opening_Date) {
		Opening_Date = opening_Date;
	}

	public String getTender_Catagory() {
		return Tender_Catagory;
	}

	public void setTender_Catagory(String tender_Catagory) {
		Tender_Catagory = tender_Catagory;
	}

	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}
    
	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
