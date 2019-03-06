import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AppContext} from '../common/app.context';
import {User} from './user';
import { Observable } from 'rxjs';

@Injectable()
export class UsersAuthenticationService{
  private httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};
    private registerUrl = `${AppContext.API_BASE_URL}/users/sign-up`;
  private loginUrl = `${AppContext.API_BASE_URL}/login`;
  private httpHeaders = {headers: new HttpHeaders({'Content-Type':'application/json'})};
  private storage = sessionStorage;
  private STORAGE_KEY = "currentUser";

  constructor(private http: HttpClient){
  }

  login(user: User){
    let formData: FormData = new FormData();   
    formData.append('username', user.username);
    formData.append('password', user.password);
    let responseUserDataObservable = this.http.post(this.loginUrl, formData);

    responseUserDataObservable.subscribe(() => {
      this.setCurrentUserSessionData(user);
    });

    return responseUserDataObservable;
  }

  logout(){
    this.storage.removeItem(this.STORAGE_KEY);
  }

  signUp(user: User){
    return this.http.post(this.registerUrl, user, this.httpHeaders);
  }

  isAuthenticated(): boolean{    
    let currentUser = this.getCurrentUserSessionData();
    return currentUser && currentUser.authdata;
  }

  getUserDetails(){
    return this.http.get(`${AppContext.API_BASE_URL}/account/details`);
  }

  getCurrentUserSessionData(){
    return JSON.parse(this.storage.getItem(this.STORAGE_KEY));
  }

  private setCurrentUserSessionData(user: User){
    let userData = {authdata: btoa(`${user.username}:${user.password}`)}        
    this.storage.setItem(this.STORAGE_KEY, JSON.stringify(userData));
  }
}
