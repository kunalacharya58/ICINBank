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
  other = false;
  selection = ''


  selfTransfer = {
    type: '',
    amt: '',
  }

  otherTransfer = {
    type: '',
    amt: '',
    accNo: '',
  }
  selectValue(){
    if(this.selection === "self"){
      this.self = true;
      this.other = false;
    } else {
      this.self = false;
      this.other = true;
    }
  }

  submitSelf(){
    if(this.selfTransfer.amt !== ''){
      this.selfTransfer['userID'] = localStorage.getItem('userId');
      this.acc.sendSelf(this.selfTransfer).subscribe(
        (resp) => {
          console.log(resp)
        },
        (err) => {
          console.log(err)
        }
      )
    }
  }

  numberOnly(event): boolean {
    const charCode = event.which ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  ngOnInit(): void {
  }

}
