import { Component, Input } from '@angular/core';
import { Listing } from '../model/listings';

@Component({
    selector: 'app-listing-card',
    templateUrl: './listing.card.html',
    styleUrls: ['../../../app.component.css']
})
export class ListingCardComponent{
    @Input() listing: Listing;
    
}