package com.businessbook.domain.listings.services;

import org.springframework.stereotype.Service;

import com.businessbook.domain.listings.model.Listing;
import com.businessbook.domain.listings.model.Product;
import com.businessbook.domain.listings.repository.ListingsRepository;

@Service
public class ListingsMaintenanceService {
	private ListingsRepository repository;
	
	/**
     * Update or add Business object
     * @param business
     * @return
     */
    public Listing save(Listing listing){
        return repository.save(listing);
    }
    
    /**
     * Add or update product for the listing
     * @param businessId
     * @param product
     * @return
     */
    public void saveProduct(Listing listing, Product product) {
    	listing.addProduct(product);
    	save(listing);    	
    }
    

    public ListingsMaintenanceService(ListingsRepository repository) {
        this.repository = repository;
    }
    
	/**
     * Delete Business by id
     * @param id
     */
    public void delete(String id) {
    	repository.deleteById(id);
    }   
}
