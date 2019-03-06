import {Component} from '@angular/core';
import {UsersAuthenticationService} from '../users.service';
import {User} from '../user';
import {Router, ActivatedRoute} from '@angular/router';
import { map } from 'rxjs/operators';


@Component({
  selector: 'users-login',
  templateUrl: 'login.html'
})

export class UsersLoginComponent{
  user: User = new User();
  badCredentials: boolean =  false;
  returnUrl: string;
  registerNewUser : boolean = false;

  constructor(private service: UsersAuthenticationService,
              private router: Router,
              private route: ActivatedRoute){}
  ngOnInit(){
    this.route.queryParams.pipe(map(p => p['returnUrl'])).subscribe(res => {
      if(res == undefined){
        res = 'account/details';
      }
      this.returnUrl = res;
    });
    this.route.queryParams.pipe(map(p => p['register'])).subscribe(res => this.registerNewUser = true);
  }

  login(){

    this.service.login(this.user).subscribe(
      () => {
        this.badCredentials = false;        
        this.router.navigate([`/${this.returnUrl}`]);
        return true;
      },
      err => {
        this.badCredentials = true;
        console.log(err);
      });
    }
}
