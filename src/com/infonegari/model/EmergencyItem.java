package com.infonegari.model;

public class EmergencyItem {
	private String title;
	private int icon;
	private String phoneNo;
	
	
	public EmergencyItem() {
	}

	public EmergencyItem(String title, int icon, String phoneNo) {
		this.title = title;
		this.icon = icon;
		this.phoneNo = phoneNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
}
