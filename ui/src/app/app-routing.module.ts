import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/components/login/login.component';
import { RegisterComponent } from './auth/components/register/register.component';
import { HomeComponent } from './user/components/home/home.component';
import { DepositComponent } from './user/components/deposit/deposit.component';
import { BaseComponent } from './user/components/base/base.component';
import { WithdrawComponent } from './user/components/withdraw/withdraw.component';
import { ChequeComponent } from './user/components/cheque/cheque.component';
import { ProfileComponent } from './user/components/profile/profile.component';
import { TransactionComponent } from './user/components/transaction/transaction.component';
import { PrimaryComponent } from './user/components/transaction/primary/primary.component';
import { SavingsComponent } from './user/components/transaction/savings/savings.component';
import { TransferComponent } from './user/components/transfer/transfer.component';
import { AdminBaseComponent } from './admin/components/admin-base/admin-base.component';
import { AdminHomeComponent } from './admin/components/admin-home/admin-home.component';
import { AdminChequebookComponent } from './admin/components/admin-chequebook/admin-chequebook.component';
import { PrivacyComponent } from './shared/component/privacy/privacy.component';
import { SecurityComponent } from './shared/component/security/security.component';
import { BankRegulationComponent } from './shared/component/bank-regulation/bank-regulation.component';
import { AdminLoginComponent } from './admin/components/admin-login/admin-login.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'user',
    component: BaseComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { path: 'deposit', component: DepositComponent },
      { path: 'withdraw', component: WithdrawComponent },
      { path: 'chequebook', component: ChequeComponent },
      { path: 'profile', component: ProfileComponent },
      { path: 'transaction', component: TransactionComponent, children: [
        {path: 'primary', component:PrimaryComponent},
        {path: 'savings', component:SavingsComponent}
      ] },
      { path: 'transfer', component: TransferComponent },
      { path: 'privacy', component: PrivacyComponent},
      { path: 'security', component: SecurityComponent},
      { path: 'bank-regulatory', component: BankRegulationComponent}
    ],
  },
  {
    path: 'admin',
    component: AdminBaseComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: AdminHomeComponent },
      { path: 'chequebookrequest', component: AdminChequebookComponent},
      { path: 'privacy', component: PrivacyComponent},
      { path: 'security', component: SecurityComponent},
      { path: 'bank-regulatory', component: BankRegulationComponent}
    ],
  },
    { path:'admin-login', component:AdminLoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
