import {Component} from '@angular/core';
import { UsersAuthenticationService } from '../../users/users.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.html' ,
  styleUrls: ['../../../app.component.css']
})
export class HeaderComponent{
  constructor(private authService: UsersAuthenticationService){

  }

  logout(){
    this.authService.logout();
    window.location.reload();
  }

}
