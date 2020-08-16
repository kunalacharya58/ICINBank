import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../account.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css', '../base/base.component.css']
})
export class TransferComponent implements OnInit {

  constructor(private acc:AccountService) { }
  self = false;
  same = false
  other = false;
  selection = ''
  errMsg = ''
  err = false
  success = false

  selfTransfer = {
    accountType: '',
    amount: '',
  }

  sameTransfer = {
    fromAccount: '',
    toAccount: '',
    toUsername: '',
    amount: '',
    transferType: ''
  }

  otherTransfer = {
    fromAccount: '',
    toAccount: '',
    toUsername: '',
    amount: '',
    transferType: ''
  }

  selectValue(){
    if(this.selection === "self"){
      this.self = true;
      this.same =false
      this.other = false;
    } else if(this.selection === "same") {
      this.same = true;
      this.self = false;
      this.other = false;
    } else {
      this.other = true;
      this.self = false;
      this.same = false;
    }
  }

  checkAllFields(form){
    for(let field in form){
      if(form[field] === '' || form[field] === null){
        return false
      }
    }
    return true
  }

  submitSelf(){
    if(this.checkAllFields(this.selfTransfer)){
      this.selfTransfer['userID'] = sessionStorage.getItem('userId');
      this.acc.sendSelf(this.selfTransfer).subscribe(
        (resp) => {
          this.err = false
          this.success = true
          this.errMsg = resp.headers.get('message')
          this.selfTransfer.amount = ''
        },
        (err) => {
          this.err = true
          this.success = false
          this.errMsg = err.headers.get('message')
        }
      )
    }
  }

  submitSame(){
    this.sameTransfer.transferType = "same"
    if(this.checkAllFields(this.sameTransfer)){
      console.log(this.sameTransfer)
      this.sameTransfer['userID'] = sessionStorage.getItem('userId');
      this.acc.sendSame(this.sameTransfer).subscribe(
        (resp) => {
          this.err = false
          this.success = true
          this.errMsg = resp.headers.get('message')
          this.sameTransfer.amount = ''
        },
        (err) => {
          this.err = true
          this.success = false
          this.errMsg = err.headers.get('message')
        }
      )
    }
  }

  submitOther(){
    console.log(this.otherTransfer)
    this.otherTransfer.transferType="other"
    if(this.checkAllFields(this.otherTransfer)){
      console.log(this.otherTransfer)
      this.otherTransfer['userID'] = sessionStorage.getItem('userId');
      this.acc.sendOther(this.otherTransfer).subscribe(
        (resp) => {
          this.err = false
          this.success = true
          this.errMsg = resp.headers.get('message')
        },
        (err) => {
          this.err = true
          this.success = false
          this.errMsg = err.headers.get('message')
        }
      )
    }
  }

  numberOnly(event): boolean {
    this.err =false
    this.success = false
    const charCode = event.which ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  ngOnInit(): void {
  }

}
