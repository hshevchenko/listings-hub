import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {AppContext} from '../../common/app.context';
import {Listing, Product, ListingsFilterCriteria} from '../model/listings';

@Injectable()
export class ListingsMaintenanceService{
    private httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
    private baseUri: string = AppContext.API_BASE_URL + '/listings';
    
    constructor(private http: HttpClient){
    }

    updateListing(id, listing: Listing){
        const url = this.baseUri + '/' +id;
        this.http.put(url, listing, this.httpOptions)
        .toPromise()
        .catch(AppContext.getErrorHandler().handleError);
        }

        addListing(listing: Listing){
            this.http.post(this.baseUri, listing, this.httpOptions).toPromise().catch(AppContext.getErrorHandler().handleError);
        }
        saveProduct(listingId, product:Product){
        const url = this.baseUri + '/' +listingId + '/products';
        
        this.http.post(url, product, this.httpOptions).toPromise().catch(AppContext.getErrorHandler().handleError);
        }

        deleteListing(listing: Listing){
            const url = this.baseUri + '/' +listing.id;
            this.http.delete(url).toPromise().catch(AppContext.getErrorHandler().handleError);
          }  
}