import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {Listing} from '../model/listings';
import {ListingsSearchService} from '../services/listings.search.service';
import { map } from "rxjs/operators";

@Component({
  selector: 'app-listing-view',
  templateUrl: './view-details.html',
  styleUrls: ['../../../app.component.css']
})

export class ListingDetailsViewComponent implements OnInit {
    private listing: Listing;
    constructor(private service: ListingsSearchService,
                private route: ActivatedRoute){
    }

    ngOnInit(){
        this.route.params
                  .pipe(map(params => params['id']))
                  .subscribe(id => this.service.getListing(id).subscribe(response => this.listing = response));
    }

   /* update(){
      this.listing.name=this.listing.name + ' - updated from Front-End!';
      this.service.updateListing(this.listing.id, this.listing);
    }*/
}
