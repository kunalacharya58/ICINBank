import { Component, OnInit } from '@angular/core';
import { UserService } from '../../user.service'
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css', '../base/base.component.css']
})
export class DepositComponent implements OnInit {

  deposit = {
    accountType : [''],
    amount : [''],
    userid : localStorage.getItem('userId')
  }

  error = false;
  errorMsg = "";

  constructor(private user: UserService, private router : Router) { }

  ngOnInit(): void {
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  submit(){
    console.log(this.deposit);
    this.user.deposit(JSON.stringify(this.deposit)).subscribe(
      (resp) => { if(resp.ok) this.router.navigate(['/account/deposit']) },
      (err:HttpErrorResponse) => {
        this.errorMsg = err.headers.get('message');
        this.error = true
     }
    )
  }
}
