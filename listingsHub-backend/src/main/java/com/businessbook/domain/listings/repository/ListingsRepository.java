package com.businessbook.domain.listings.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.businessbook.domain.listings.model.Listing;



@Repository
public interface ListingsRepository extends MongoRepository<Listing, String>, ListingsRepositoryCustom{
	@Query(value="{userId:?0}", fields = "{name: 1, category: 1, address: 1}")
	List<Listing> findAllLightweight(String userId);
	
	List<Listing> findByUserId(String userId);
}
