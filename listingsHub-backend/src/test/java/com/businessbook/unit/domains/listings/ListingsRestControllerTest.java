package com.businessbook.unit.domains.listings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import com.businessbook.config.security.SecurityConfiguration;
import com.businessbook.domain.listings.controllers.ListingsSearchRestController;
import com.businessbook.domain.listings.maintenance.model.Listing;
import com.businessbook.domain.listings.maintenance.model.ListingCategory;
import com.businessbook.domain.users.controllers.UsersRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ListingsRestControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ListingsSearchRestController mockListingsRestController;
	
	@Test
	public void testGetListings() throws Exception {
		List<Listing> list = buildListingsList();
		
		//given(mockListingsRestController.getListingById(null)).willReturn(list);
		
		mvc.perform(get("/listings")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)));
	}
	
	private List<Listing> buildListingsList(){
		List<Listing> list = new ArrayList<>();
		list.add(buildListing("1", "NAME_1", ListingCategory.SALE));
		list.add(buildListing("2", "NAME_2", ListingCategory.SHOP));
		list.add(buildListing("3", "NAME_3", ListingCategory.SERVICE));
		return list;
	}
	
	private Listing buildListing(String id, String name, ListingCategory category) {
		Listing listing = new Listing();
		listing.setId(id);
		listing.setCategory(category);
		listing.setName(name);
		return listing;
	}
}
