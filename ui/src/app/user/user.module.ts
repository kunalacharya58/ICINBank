import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { TransferComponent } from './components/transfer/transfer.component';
import { DepositComponent } from './components/deposit/deposit.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { WithdrawComponent } from './components/withdraw/withdraw.component';
import { BaseComponent } from './components/base/base.component';
import { ChequeComponent } from './components/cheque/cheque.component';
import { FormsModule } from '@angular/forms';
import { PrimaryComponent } from './components/transaction/primary/primary.component';
import { SavingsComponent } from './components/transaction/savings/savings.component';



@NgModule({
  declarations: [NavbarComponent, HomeComponent, TransferComponent, DepositComponent, ProfileComponent, RegisterComponent, TransactionComponent, WithdrawComponent, BaseComponent, ChequeComponent, PrimaryComponent, SavingsComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule
  ]
})

export class UserModule { }
