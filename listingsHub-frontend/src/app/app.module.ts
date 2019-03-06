import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS }    from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { ListingSearchCriteriaComponent } from './domains/listings/components/search';
import { ListingDetailsViewComponent } from './domains/listings/components/view-details';
import { ListingDetailsEditComponent } from './domains/listings/components/edit-details';
import {HeaderComponent} from './domains/common/components/header';
import {UsersLoginComponent } from './domains/users/components/login';
import { AccountDetailsComponent } from './domains/users/components/account-details';

import {ListingsSearchService} from './domains/listings/services/listings.search.service';
import {UsersAuthenticationService} from './domains/users/users.service';

import {BasicAuthenticationInterceptor} from './domains/platform/security/basic.auth.interceptor';
import { ListingsMaintenanceService } from './domains/listings/services/listings.maintenance.service';
import { ListingCardComponent } from './domains/listings/components/listing.card';

@NgModule({
  declarations: [
    AppComponent,
    ListingSearchCriteriaComponent,
    ListingDetailsViewComponent,
    ListingDetailsEditComponent,
    HeaderComponent,
    UsersLoginComponent,
    AccountDetailsComponent,
    ListingCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    ListingsSearchService,
    ListingsMaintenanceService,
    UsersAuthenticationService,
    {provide: HTTP_INTERCEPTORS, useClass: BasicAuthenticationInterceptor, multi: true}

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
