import { Component, OnInit } from '@angular/core';
import {Listing, ListingsFilterCriteria} from '../model/listings';
import {ListingsSearchService} from '../services/listings.search.service';

@Component({
  selector: 'app-business-search-criteria',
  templateUrl: './search.html',
  styleUrls: ['../../../app.component.css']
})
export class ListingSearchCriteriaComponent implements OnInit {
  private listings: Listing[];
  searchCriteria: ListingsFilterCriteria;
  testMessage: string;

  constructor(private service: ListingsSearchService) { }

  ngOnInit() {    
  }

  find(){
    //this.service.search(this.searchCriteria).subscribe(response => this.listings = response);
    this.service.search().subscribe(response => {
      this.listings = response as Listing[];
    });
  }

  /*delete(listing){
    this.service.deleteListing(listing);
    this.find();
  }*/
}
