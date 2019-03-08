import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListingsHomeComponent } from './domains/listings/components/home';
import { ListingDetailsViewComponent } from './domains/listings/components/view-details';
import { ListingDetailsEditComponent } from './domains/listings/components/edit-details';
import {UsersLoginComponent } from './domains/users/components/login';
import { AuthenticationGuard } from './domains/platform/security/auth.guard';
import { AccountDetailsComponent } from './domains/users/components/account-details';



const routes: Routes = [ 
  {path: '', component: ListingsHomeComponent},
  {path: 'login', component: UsersLoginComponent},
  

  {path: 'account/details', component: AccountDetailsComponent, canActivate: [AuthenticationGuard]},

  {path: 'listing/:id', component: ListingDetailsViewComponent}, 
  {path: 'listing-edit/:id', component: ListingDetailsEditComponent, canActivate: [AuthenticationGuard]},
  {path: 'listing-edit', component: ListingDetailsEditComponent, canActivate: [AuthenticationGuard]}  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
