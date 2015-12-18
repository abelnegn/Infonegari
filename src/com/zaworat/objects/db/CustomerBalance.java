package com.zaworat.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class CustomerBalance extends SugarRecord<CustomerBalance>{

	private String customerName;
	private String amount;
	private String balanceDate;
	
    public boolean isNew() {
        long count = Select.from(CustomerBalance.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public String getBalanceDate() {
		return balanceDate;
	}



	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}



	@Override
    public String toString() {
        return new Gson().toJson(this);
    }
}