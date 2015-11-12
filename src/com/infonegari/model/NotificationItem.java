package com.infonegari.model;

public class NotificationItem {
	private String title;
	private boolean isChecked = false;
	
	
	public NotificationItem() {
	}

	public NotificationItem(String title, boolean isChecked) {
		this.title = title;
		this.isChecked = isChecked;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
}
