//The auth guard is used to prevent unauthenticated users from accessing restricted routes.

import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UsersAuthenticationService } from '../../users/users.service';

@Injectable({ providedIn: 'root' })
export class AuthenticationGuard implements CanActivate {

    constructor(private router: Router, 
                private service: UsersAuthenticationService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (this.service.isAuthenticated()) {
            // logged in so return true
            return true;
        }

        // not logged in so redirect to login page with the return url
        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        return false;
    }
}
