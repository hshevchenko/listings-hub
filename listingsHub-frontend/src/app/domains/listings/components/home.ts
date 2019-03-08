import { Component, OnInit } from '@angular/core';
import {Listing, ListingsFilterCriteria} from '../model/listings';
import {ListingsSearchService} from '../services/listings.search.service';

@Component({
  selector: 'home',
  templateUrl: './home.html',
  styleUrls: ['../../../app.component.css']
})

export class ListingsHomeComponent implements OnInit {
  private listings: Listing[];
  filter: ListingsFilterCriteria = new ListingsFilterCriteria();
  testMessage: string;

  constructor(private service: ListingsSearchService) { }

  ngOnInit() {    
  }

  search(){
    //this.service.search(this.searchCriteria).subscribe(response => this.listings = response);
    this.service.search(this.filter).subscribe(response => {
      this.listings = response as Listing[];
    });
  }

  /*delete(listing){
    this.service.deleteListing(listing);
    this.find();
  }*/
}
