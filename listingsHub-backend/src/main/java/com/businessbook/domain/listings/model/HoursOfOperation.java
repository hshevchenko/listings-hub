package com.businessbook.domain.listings.model;

import java.util.Date;

public class HoursOfOperation {
	private Date date;
	private Byte day;
	private byte startTime;
	private byte endTime;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Byte getDay() {
		return day;
	}
	public void setDay(Byte day) {
		this.day = day;
	}
	public byte getStartTime() {
		return startTime;
	}
	public void setStartTime(byte startTime) {
		this.startTime = startTime;
	}
	public byte getEndTime() {
		return endTime;
	}
	public void setEndTime(byte endTime) {
		this.endTime = endTime;
	}
	

}
