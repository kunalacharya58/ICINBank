import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module'
import { HttpClientModule } from "@angular/common/http";
import { UserModule } from './user/user.module';
import { AdminBaseComponent } from './admin/components/admin-base/admin-base.component';
import { AdminHomeComponent } from './admin/components/admin-home/admin-home.component'
import { AdminModule } from "./admin/admin.module";
import { AdminChequebookComponent } from './admin/components/admin-chequebook/admin-chequebook.component';
@NgModule({
  declarations: [
    AppComponent,
    AdminBaseComponent,
    AdminHomeComponent,
    AdminChequebookComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    HttpClientModule,
    UserModule,
    AdminModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
