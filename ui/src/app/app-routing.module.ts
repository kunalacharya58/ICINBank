import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { PrivacyComponent } from './common/privacy/privacy.component'
import { SecurityComponent } from './common/security/security.component'
import { BriComponent } from './common/bri/bri.component'

const routes: Routes = [
  { path: "", redirectTo: 'login', pathMatch:'full'},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'privacy', component: PrivacyComponent },
  { path: 'security', component: SecurityComponent },
  { path: 'bri', component: BriComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
