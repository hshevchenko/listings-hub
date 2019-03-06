package com.businessbook.domain.listings.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import com.businessbook.domain.listings.model.Listing;
import com.businessbook.domain.listings.model.ListingCategory;
import com.businessbook.domain.listings.model.ListingFilterCriteria;
import com.businessbook.domain.listings.repository.ListingsRepository;

/*
 * TODO:
 * 1. Listings Search vs Listings Maintenance - when search for listing user or guest can see all publis listings. When in
 * 		maintenance mode only see what's applicable for user
 * 2. 
 */
@Service
public class ListingsSearchService {
	private ListingsRepository repository;
		

    public ListingsSearchService(ListingsRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Get list of all businesses in the system
     * @return lightweight objects, just couple of fields populated
     */
    public List<Listing> getListingsForUser(String userId){
    	//TODO validate that user in context and userId is the same- authorize access 
    	return repository.findAllLightweight(userId);
    }
    
    /**
     * Find Business by id
     * @param id
     * @return
     */
    public Listing getListing(String id) {
    	return repository.findById(id).get();
    }
    
    /**
     * Find by search criteria populated by user
     * @param criteria
     * @return
     */
    public List<Listing> search(ListingFilterCriteria criteria){
    	return repository.findByCriteria(criteria);
    }        
    
    
    /**
     * Get list of all business categories
     * @return
     */
    public List<ListingCategory> getListingCategories(){
    	return Arrays.asList(ListingCategory.values());
    }
}
