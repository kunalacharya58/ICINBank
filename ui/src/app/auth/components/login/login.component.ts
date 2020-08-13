import { Component, OnInit } from '@angular/core';
import { FormGroup,FormBuilder } from "@angular/forms";
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from "../../auth.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form:FormGroup
  error = false
  errorMsg = ""
  constructor(public fb:FormBuilder, private auth:AuthService, private router:Router) {
    this.form = this.fb.group({
      username: [''],
      password: [''],
    })
  }




  submit(){
    this.auth.login(this.form.value).subscribe(
      (resp) => { if(resp.ok) this.router.navigate(['user/home']) },
      (err:HttpErrorResponse) => {
        this.errorMsg = err.headers.get('message');
        this.error = true
     }
    )
  }
  ngOnInit(): void {
  }

}
