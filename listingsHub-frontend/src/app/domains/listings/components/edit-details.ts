import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Listing, Product} from '../model/listings';
import {ListingsSearchService} from '../services/listings.search.service';
import { map } from "rxjs/operators";
import { ListingsMaintenanceService } from '../services/listings.maintenance.service';

@Component({
  selector: 'app-listing-edit',
  templateUrl: './edit-details.html',
  styleUrls: ['../../../app.component.css']
})

export class ListingDetailsEditComponent implements OnInit{
  listing: Listing;
  categories: string[];
  productInProgress: Product;
  isNewListing: boolean = false;

  constructor(private searchService: ListingsSearchService,
              private crudService: ListingsMaintenanceService,
              private route: ActivatedRoute,
              private router: Router){
  }

  ngOnInit(){
      this.searchService.getCategories().subscribe(response => this.categories = response as string[]);
      this.route.params
      .pipe(map((params: Params) => params['id']))
      .subscribe(id => {
        if(id != null){
          this.searchService.getListing(id).subscribe(response => this.listing = response);
        }
        else{
          this.listing = new Listing();
          this.isNewListing = true;
        }
      });


  }

  update(){
    if(this.isNewListing){
      this.crudService.addListing(this.listing);
    }
    else{
      this.crudService.updateListing(this.listing.id, this.listing);
    }
    this.router.navigateByUrl('/');
  }

  addProduct(){
    this.productInProgress = new Product();
  }

  deleteProduct(){
    if(this.productInProgress != null){
      const name= this.productInProgress.name;
      this.listing.products = this.listing.products.filter(function (product, index, arr){
          return false ;
      });
      console.log('here');
      console.log(this.listing.products);
      this.productInProgress = null;
    }
  }

  editProduct(product: Product){
    console.log(product);
    this.productInProgress = product;
  }
  saveProduct(){
    //this.service.saveProduct(this.listing.id, this.productInProgress);
    if(this.productInProgress.id == null){
      this.listing.products.push(this.productInProgress);
    }
    this.productInProgress = null;
  }
}
