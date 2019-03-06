import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../user';
import { UsersAuthenticationService } from '../users.service';
import { Listing } from '../../listings/model/listings';

@Component({
    selector: 'account-details',
    templateUrl: 'account-details.html'
  })

  export class AccountDetailsComponent implements OnInit{
    private user: User;
    private listings: Listing[];

    constructor(private http: HttpClient,
                private userService: UsersAuthenticationService){

    }

    ngOnInit(){
      this.userService.getUserDetails().subscribe(res=> this.user as User);
    }

  }