package com.infonegari.objects.db;

import java.util.Date;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class SalesAuction extends SugarRecord<SalesAuction>{
	private long SalesAuctionId;
	private String SalesAuctionName;
	private Date StartDate;
	private String Category;
	private int SalesItemType;
	private long MemberId;
	private String SalesAuctionSource;
	
    public boolean isNew() {
        long count = Select.from(SalesAuction.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }


	public long getSalesAuctionId() {
		return SalesAuctionId;
	}


	public void setSalesAuctionId(long salesAuctionId) {
		SalesAuctionId = salesAuctionId;
	}


	public String getSalesAuctionName() {
		return SalesAuctionName;
	}


	public void setSalesAuctionName(String salesAuctionName) {
		SalesAuctionName = salesAuctionName;
	}


	public Date getStartDate() {
		return StartDate;
	}


	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}


	public String getCategory() {
		return Category;
	}


	public void setCategory(String category) {
		Category = category;
	}


	public int getSalesItemType() {
		return SalesItemType;
	}


	public void setSalesItemType(int salesItemType) {
		SalesItemType = salesItemType;
	}


	public long getMemberId() {
		return MemberId;
	}


	public void setMemberId(long memberId) {
		MemberId = memberId;
	}


	public String getSalesAuctionSource() {
		return SalesAuctionSource;
	}


	public void setSalesAuctionSource(String salesAuctionSource) {
		SalesAuctionSource = salesAuctionSource;
	}


	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
