package com.businessbook.domain.listings.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value="listings")
public class Listing {
	@Id
	private String id;
	private String userId;
	private	String name;
	private String description;
	private ListingCategory category;
	private Address address = new Address();
	private ContactInformation contacts = new ContactInformation();
	private Set<Product> products = new HashSet<>();
	private Set<PaymentMethod> paymentMethods = new HashSet<>();
	private Set<HoursOfOperation> hoursOfOperation = new HashSet<>();
	private float averageRating = 0.0f;
	private Reviews reviews= new Reviews();
	
		
	public ContactInformation getContacts() {
		return contacts;
	}
	public void setContacts(ContactInformation contacts) {
		this.contacts = contacts;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ListingCategory getCategory() {
		return category;
	}
	public void setCategory(ListingCategory category) {
		this.category = category;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Set<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}
	public void setPaymentMethods(Set<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	public Set<HoursOfOperation> getHoursOfOperation() {
		return hoursOfOperation;
	}
	public void setHoursOfOperation(Set<HoursOfOperation> hoursOfOperation) {
		this.hoursOfOperation = hoursOfOperation;
	}
	public float getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}
	public Reviews getReviews() {
		return reviews;
	}
	public void setReviews(Reviews reviews) {
		this.reviews = reviews;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Product getProduct(String productId) {
		Product retVal = null;
		if(id != null && StringUtils.isNotBlank(productId)) {
			retVal = getProducts().stream().filter(product -> product.getId().equals(productId)).findFirst().get();
		}
		
		return retVal;
	}
	
	public void addProduct(Product product){
		products.add(product);
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
