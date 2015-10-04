package com.infonegari.objects.db;

import java.util.Date;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Auction extends SugarRecord<Auction>{
	private long auction_id;
	private String Company_Name;
	private String Phone_Number;
	private Date Post_Date;
	private Date Submission_Deadline;
	private Date Opening_Date;
	private long Auction_Catagory;
	private String Description;
	private float Minimum_Price;
	private String Source;

    public boolean isNew() {
        long count = Select.from(Auction.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }
    
    
	public long getAuction_id() {
		return auction_id;
	}


	public void setAuction_id(long auction_id) {
		this.auction_id = auction_id;
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


	public Date getPost_Date() {
		return Post_Date;
	}


	public void setPost_Date(Date post_Date) {
		Post_Date = post_Date;
	}


	public Date getSubmission_Deadline() {
		return Submission_Deadline;
	}


	public void setSubmission_Deadline(Date submission_Deadline) {
		Submission_Deadline = submission_Deadline;
	}


	public Date getOpening_Date() {
		return Opening_Date;
	}


	public void setOpening_Date(Date opening_Date) {
		Opening_Date = opening_Date;
	}


	public long getAuction_Catagory() {
		return Auction_Catagory;
	}


	public void setAuction_Catagory(long auction_Catagory) {
		Auction_Catagory = auction_Catagory;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public float getMinimum_Price() {
		return Minimum_Price;
	}


	public void setMinimum_Price(float minimum_Price) {
		Minimum_Price = minimum_Price;
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
