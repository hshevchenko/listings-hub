package com.businessbook.domain.listings.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.businessbook.domain.common.AppContext;
import com.businessbook.domain.listings.model.Listing;
import com.businessbook.domain.listings.model.ListingFilterCriteria;
import com.businessbook.domain.listings.model.PaymentMethod;
import com.businessbook.domain.listings.model.Product;
import com.businessbook.domain.listings.services.ListingsMaintenanceService;
import com.businessbook.domain.listings.services.ListingsSearchService;

@RestController
@RequestMapping("/account/listings")
public class ListingsMaintenanceRestController {	
	private ListingsMaintenanceService service;
	private ListingsSearchService searchService;	

	public ListingsMaintenanceRestController(ListingsMaintenanceService service, ListingsSearchService searchService) {
		super();
		this.service = service;
		this.searchService = searchService;
	}
	
	@GetMapping("")
	public List<Listing> getListingsForAccount(){
		System.out.println(AppContext.getUser());
		return searchService.search(new ListingFilterCriteria());
	}
	/**
     * Add new listing
     * @param newListing
     * @return newly created listing
     */
    @PostMapping("")
    public Listing addListing(@RequestBody Listing newListing){
    	newListing.getPaymentMethods().addAll(Arrays.asList(PaymentMethod.values()));    	
    	return service.save(newListing);
    }
    
    /**
     * Add/update product for the listing
     * @param listingId
     * @param product
     * @return updated Product
     */
    @PostMapping("/{id}/products")
    public void saveProduct(@PathVariable("id") String listingId, @RequestBody Product product) {
    	Listing listing = searchService.getListing(listingId);
    	if(listing != null) {
    		Product productExisting = listing.getProduct(product.getId());
    		if(productExisting != null) {
    			productExisting.setName(product.getName());
    			productExisting.setDescription(product.getDescription());
    			productExisting.setPrice(product.getPrice());
    		}
    		else {
    			productExisting = product;
    		}    		
    		service.saveProduct(listing, productExisting);
    	}    	
    }
    
    /**
     * Update existing listing
     * @param id
     * @param listingUpdated
     * @return
     */
    @PutMapping("/{id}")
    public Listing updateListing(@PathVariable("id") String id, @RequestBody Listing listingUpdated) {    	
    	return service.save(listingUpdated);
    }
    
    /**
     * Delete listing by id
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteBusinessById(@PathVariable("id") String listingId) {
    	service.delete(listingId);
    }	

}
