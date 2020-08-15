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
  ngOnInit(): void {
  }
  
  submit(){
    console.log(this.accoutType)
    this.userService.requestCB(this.accoutType).subscribe(
      (res)=>{
        
          this.error = false
          this.success = true
          this.errorMsg = res.headers.get('message')
        
        console.log(res)
      },
      (err:HttpErrorResponse) =>{
        this.errorMsg = err.headers.get('message');
        this.error = true;
        this.success = false;
        if(err.status === 400){
          this.errorMsg = "enter complete information";
        }
        console.log(err)
      }
    );
    
  }

}
