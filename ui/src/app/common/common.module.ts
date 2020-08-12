import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { PrivacyComponent } from './privacy/privacy.component';
import { SecurityComponent } from './security/security.component';
import { BriComponent } from './bri/bri.component';



@NgModule({
  declarations: [FooterComponent, PrivacyComponent, SecurityComponent, BriComponent],
  imports: [
    CommonModule
  ]
})
export class CommonModule { }
