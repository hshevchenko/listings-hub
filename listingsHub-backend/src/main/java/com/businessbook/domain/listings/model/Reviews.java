package com.businessbook.domain.listings.model;

import java.util.ArrayList;
import java.util.List;

public class Reviews {
	private String id;
	private String businessId;
	private List<Review> reviews = new ArrayList<>();
	
	public static class Review{		
		private String reviewerName;
		private String review;
		private int rating;
		public String getReviewerName() {
			return reviewerName;
		}
		public void setReviewerName(String reviewerName) {
			this.reviewerName = reviewerName;
		}
		public String getReview() {
			return review;
		}
		public void setReview(String review) {
			this.review = review;
		}
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	

}
