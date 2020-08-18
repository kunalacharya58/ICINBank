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
import { PrivacyComponent } from './shared/component/privacy/privacy.component';
import { SecurityComponent } from './shared/component/security/security.component';
import { BankRegulationComponent } from './shared/component/bank-regulation/bank-regulation.component';
import { FormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    AppComponent,
    AdminBaseComponent,
    AdminHomeComponent,
    AdminChequebookComponent,
    PrivacyComponent,
    SecurityComponent,
    BankRegulationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    HttpClientModule,
    UserModule,
    AdminModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
