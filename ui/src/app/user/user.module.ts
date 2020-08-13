import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [NavbarComponent, HomeComponent],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})

export class UserModule { }
