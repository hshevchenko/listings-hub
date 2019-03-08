import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Listing, Product, ListingsFilterCriteria} from '../model/listings';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {AppContext} from '../../common/app.context';

@Injectable()
export class ListingsSearchService{
    private httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
    private baseUri: string = AppContext.API_BASE_URL + '/listings';

    constructor(private http: HttpClient){
    }

    search(criteria: ListingsFilterCriteria){
      return this.http.post(`${this.baseUri}/search`, criteria, this.httpOptions);
    }

    getListings(): Promise<Array<Listing>>{
        return this.http.get(this.baseUri)
        .toPromise()
        .then(response => response as Listing[])
        .catch(AppContext.getErrorHandler().handleError);

    }

    getListing(id): Observable<Listing>{
      const url = this.baseUri + '/' +id;
      return this.http.get<Listing>(url);
    }    

    getCategories(){
      return this.http.get(this.baseUri + '/categories');
    }      
}
