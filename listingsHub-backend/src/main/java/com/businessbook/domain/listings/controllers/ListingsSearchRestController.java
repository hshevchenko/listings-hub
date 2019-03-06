package com.businessbook.domain.listings.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.businessbook.domain.listings.model.Listing;
import com.businessbook.domain.listings.model.ListingCategory;
import com.businessbook.domain.listings.model.ListingFilterCriteria;
import com.businessbook.domain.listings.services.ListingsSearchService;




@RestController
@RequestMapping("/listings")
public class ListingsSearchRestController {	
	
	private ListingsSearchService service;

    public ListingsSearchRestController(ListingsSearchService service) {
        this.service = service;
    }
    
    /**
     * Get list of all listings in the system. LIsting representation is lightweight and doesn't have all information
     * Paginated
     * @return
     */
    @GetMapping("")
    public List<Listing> getListingsForUser(@RequestParam("user-id") String userId){    	
        return service.getListingsForUser(userId);
    }
    
    /**
     * Find listings by filter criteria
     * @param filter criteria
     * @return list with lightweight representation of listings
     */
    @GetMapping("/search")
    public List<Listing> searchByCriteria(@RequestBody ListingFilterCriteria criteria){
    	return service.search(criteria);
    }
    
    /**
     * Find listings by filter criteria
     * @param filter criteria
     * @return list with lightweight representation of listings
     */
    @GetMapping("/all")
    public List<Listing> search(){
    	return service.search(new ListingFilterCriteria());
    }
    
    /**
     * Finds listing by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Listing getListingById(@PathVariable("id") String id ) {
    	return service.getListing(id);
    }
    
    /**
     * Get list of all categories of listings
     * @return
     */
    @GetMapping("/categories")
    public List<ListingCategory> getCategories(){
    	return service.getListingCategories();
    }
        
}
