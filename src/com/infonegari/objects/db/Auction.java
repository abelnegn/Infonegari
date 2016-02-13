package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Auction extends SugarRecord<Auction>{
	private long auction_id;
	private String Company_Name;
	private String Phone_Number;
	private String Post_Date;
	private String Submission_Deadline;
	private String Opening_Date;
	private long Auction_Catagory;
	private String Description;
	private String Minimum_Price;
	private String Source;
	private String User_Name;

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



	public String getMinimum_Price() {
		return Minimum_Price;
	}


	public void setMinimum_Price(String minimum_Price) {
		Minimum_Price = minimum_Price;
	}


	public String getSource() {
		return Source;
	}


	public void setSource(String source) {
		Source = source;
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
