package com.businessbook.domain.listings.repository;

import java.util.List;

import com.businessbook.domain.listings.model.Listing;
import com.businessbook.domain.listings.model.ListingFilterCriteria;

public interface ListingsRepositoryCustom {
	/**
	 * Find by search criteria
	 * @param criteria
	 * @return
	 */
	List<Listing> findByCriteria(ListingFilterCriteria criteria);
	
}
