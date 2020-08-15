import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { HomeComponent } from './user/components/home/home.component'
import { DepositComponent } from './user/components/deposit/deposit.component';
import { BaseComponent } from './user/components/base/base.component';
import { WithdrawComponent } from './user/components/withdraw/withdraw.component';
import { ChequeComponent } from './user/components/cheque/cheque.component';
import { ProfileComponent } from './user/components/profile/profile.component';

const routes: Routes = [
  { path: "", redirectTo: 'login', pathMatch:'full'},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user', component: BaseComponent, children:[
    { path: "", redirectTo: 'home', pathMatch:'full'},
    { path: 'home', component: HomeComponent },
    { path: 'deposit', component: DepositComponent },
    { path: 'withdraw', component: WithdrawComponent },
    { path: 'chequebook', component: ChequeComponent },
    { path: 'profile', component: ProfileComponent }
  ]},
 ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
