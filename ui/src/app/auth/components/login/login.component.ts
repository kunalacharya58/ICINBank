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
  
  usernameError = false
  usernameErrorMessage = "Username should contain only alphabets or numbers"

  passError = false
  passErrorMessage = "must contain atleast 6 characters"
  
  
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

  haveSpecialCharacters(str : string){
    let regex = new RegExp(/^[0-9a-zA-Z]+$/);
    return !regex.test(str);
  }
  
  isEmpty(str : string){
    return (!str || str.length === 0 || str.length === undefined)
  }

  validateUserName(){ 
    
    let username = this.form.get('username').value
    this.usernameError = this.haveSpecialCharacters(username) && !this.isEmpty(username)
    return !this.usernameError
  }

  validatePassword(){

    let password = this.form.get('password').value
    this.passError = (password.length < 6) && !this.isEmpty(password)
    return !this.passError
  }

  validateFormAndSubmit(){
    if(!this.validateUserName())
        document.getElementById("lCustId").focus();
    else if(!this.validatePassword())
        document.getElementById("lpass").focus();
    else{
      this.submit();
    }
  }
}
