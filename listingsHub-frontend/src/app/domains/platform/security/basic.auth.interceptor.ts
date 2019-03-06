/** The Basic Authentication Interceptor intercepts http requests
from the application to add basic authentication credentials to the Authorization header
if the user is logged in.
*/
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inject } from '@angular/core';
import { UsersAuthenticationService } from '../../users/users.service';

export class BasicAuthenticationInterceptor implements HttpInterceptor{

    constructor(@Inject(UsersAuthenticationService) private service: UsersAuthenticationService){
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {        
        if(this.service.isAuthenticated()){
            let currentUser = this.service.getCurrentUserSessionData();
            request = request.clone({
                setHeaders: {
                    Authorization: `Basic ${currentUser.authdata}`
                }
            });
        }        
        
        return next.handle(request);
    }
}
