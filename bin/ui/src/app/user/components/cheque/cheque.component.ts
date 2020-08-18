import { Component, OnInit } from '@angular/core';
import { UserService } from '../../user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-cheque',
  templateUrl: './cheque.component.html',
  styleUrls: ['./cheque.component.css','../base/base.component.css']
})
export class ChequeComponent implements OnInit {

  constructor(private userService : UserService) { }

  accoutType : '';
  error = false;
  success = false;
  errorMsg = '';
  requestMsg = 'chequebook request pending for :'

  pending = {
    savings: false,
    primary: false
  }

  disable = false
  ngOnInit(): void {
    this.userService.checkRequest().subscribe(
      (resp) => {
        resp.forEach((item) => {
          this.errorMsg += ''
          if(item.accountType === 'Primary'){
            this.pending.primary = true
            this.requestMsg += ' primary account,'
          }

          else if(item.accountType === 'Savings'){
            this.pending.savings = true
            this.requestMsg += ' savings account,'

          }
          else {
            this.pending.primary = false;
            this.pending.savings = false;
          }
        }),
      (err) => {
        console.log(err)
      }
      }
    )
  }

  submit(){
  this.disable = true
    this.userService.requestCB(this.accoutType).subscribe(
      (res)=>{
          this.error = false
          this.success = true
          this.accoutType = undefined
          this.errorMsg = res.headers.get('message')
          setTimeout(()=>{location.reload()},1500)
          this.disable=false
      },
      (err:HttpErrorResponse) =>{
        this.errorMsg = err.headers.get('message');
        this.error = true;
        this.success = false;
        if(this.accoutType === undefined){
          this.errorMsg = "enter complete information";
        }
        this.disable=false
      }
    );

  }

}
