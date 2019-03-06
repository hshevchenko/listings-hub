package com.businessbook.domain.listings.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.businessbook.domain.listings.model.Listing;
import com.businessbook.domain.listings.model.ListingFilterCriteria;


public class ListingsRepositoryCustomImpl implements ListingsRepositoryCustom{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Listing> findByCriteria(ListingFilterCriteria criteria) {
		
		Query query = new Query();		
		if(criteria!= null && StringUtils.isNotBlank(criteria.getName())) {
			query.addCriteria(Criteria.where("name").regex(criteria.getName()));
		}
		if(criteria!= null && criteria.getCategory() != null) {
			query.addCriteria(Criteria.where("category").regex(criteria.getCategory().toString()));
		}
		if(criteria!= null && criteria.getAddress() != null) {
			query.addCriteria(Criteria.where("address").regex(criteria.getAddress()));
		}	
		if(criteria!= null && criteria.getUserId()!= null) {
			query.addCriteria(Criteria.where("userId").regex(criteria.getUserId()));
		}
		return mongoTemplate.find(query, Listing.class);		
	}

}
