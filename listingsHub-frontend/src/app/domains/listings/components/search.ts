import { Component, Input, OnInit } from '@angular/core';
import { ListingsFilterCriteria } from '../model/listings';
import { HttpClient } from '@angular/common/http';

@Component({
    selector:'listings-search-results',
    templateUrl: 'search.html',
    styleUrls: ['../../../app.component.css']
})
export class ListingsSearchComponent implements OnInit{
    @Input() filter: ListingsFilterCriteria;

    constructor(private http: HttpClient){
    }
    
    ngOnInit(){
        
    }

    search(){
        //this.service.search(this.searchCriteria).subscribe(response => this.listings = response);
        this.service.search(this.filter).subscribe(response => {
          this.listings = response as Listing[];
        });
      }

}