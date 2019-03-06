package com.businessbook.domain.listings.model;

public class ListingFilterCriteria {
	private String userId;
	private String name;
	private String address;
	private ListingCategory category;	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ListingCategory getCategory() {
		return category;
	}
	public void setCategory(ListingCategory category) {
		this.category = category;
	}
	
	

}
