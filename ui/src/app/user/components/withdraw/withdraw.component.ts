import { Component, OnInit } from '@angular/core';
import { UserService } from '../../user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css', '../base/base.component.css']
})
export class WithdrawComponent implements OnInit {

  withdraw = {
    accountType: [''],
    amount: [''],
    userID: localStorage.getItem('userId'),
  };

  error = false;
  errorMsg = '';
  success = false;

  constructor(private user:UserService) { }

  ngOnInit(): void {
  }

  numberOnly(event): boolean {
    const charCode = event.which ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  submit() {
    this.user.withdraw(JSON.stringify(this.withdraw)).subscribe(
      (resp) => {
        console.log(resp.status)
        if (resp.ok) {
          this.error = false
          this.success = true;
          this.errorMsg = resp.headers.get('message')
        };

      },
      (err: HttpErrorResponse) => {
        this.errorMsg = err.headers.get('message');
        this.error = true;
        this.success = false;
        if(err.status === 400){
          this.errorMsg = "enter complete information";
        }
      }
    );
  }

}
