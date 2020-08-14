import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

   validationErrorMessage = {
    username :[
      { name : "isAlNum", message : "Username should contain only alphabets or numbers", isError : false},
    ],
    email :[
      { name : "validEmail", message : "Invalid Email", isError : false }
    ],
    phone :[
      { name : "length", message : "must contain 10 digits", isError : false },
      { name : "isNum", message: "must contain only numerical values" , isError : false} 
    ],
    password : [
      { name : "length", message : "must contain atleast 6 characters", isError : false },
      { name : "haveNum", message : "must contain atleast one number" , isError : false},
      { name : "haveUpper", message : "must contain atleast one uppercase character", isError : false },
      { name : "haveLower", message : "must contain atleast one lowercase character" , isError : false},
      { name : "haveSpecial", message : "must contain atleast one special character", isError : false } 
    ],
    confPass : [
      { name : "passwordMatch", message : "passwords do not match", isError : false},
    ]
  }

  form:FormGroup
  error = false
  errorMsg = ""
  constructor(private fb:FormBuilder, private auth:AuthService, private router:Router) {
    this.form = this.fb.group({
      username: [''],
      firstName: [''],
      lastName: [''],
      email: [''],
      phone: [''],
      dob: Date,
      address: [''],
      password: [''],
      confPass: [''],
    })
   }

   submit(){

    this.auth.register(this.form.value).subscribe(
      (resp) => { if(resp.ok) this.router.navigate(['/login']) },
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

  haveUpper(str : string){
    let regex = new RegExp(/[A-Z]/);
    return regex.test(str);
  }

  haveLower(str : string){
    let regex = new RegExp(/[a-z]/);
    return regex.test(str);
  }

  haveNumber(str : string){
    let regex = new RegExp(/[0-9]/);
    return regex.test(str);
  }

  onlyNumbers(str : string){
    let regex = new RegExp(/^[0-9]+$/);
    return regex.test(str);
  }

  isEmpty(str : string){
    return (!str || str.length === 0 || str.length === undefined)
  }

  validateUserName(){ 
    
    let username = this.form.get('username').value
    this.validationErrorMessage.username[0].isError = this.haveSpecialCharacters(username) && !this.isEmpty(username)
    return !this.validationErrorMessage.username.find(i=> i.name == 'isAlNum').isError
  }
  
  validateEmail(){
    
    let regex = new RegExp(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/);
    let email = this.form.get('email').value

    this.validationErrorMessage.email[0].isError = !this.isEmpty(email) && !regex.test(email)
    return !this.validationErrorMessage.email[0].isError
  }

  validatePhone(){
    let phone = this.form.get('phone').value
    
    this.validationErrorMessage.phone[0].isError = phone.length != 10 && !this.isEmpty(phone)
    this.validationErrorMessage.phone[1].isError = !this.onlyNumbers(phone) && !this.isEmpty(phone)
    return !this.validationErrorMessage.phone[0].isError && !this.validationErrorMessage.phone[1].isError;
  }

  
  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  validatePassword(){
    let password = this.form.get('password').value
    
    this.validationErrorMessage.password[0].isError = password.length < 6 && !this.isEmpty(password)
    this.validationErrorMessage.password[1].isError = !this.haveNumber(password) && !this.isEmpty(password)
    this.validationErrorMessage.password[2].isError = !this.haveUpper(password) && !this.isEmpty(password)
    this.validationErrorMessage.password[3].isError = !this.haveLower(password) && !this.isEmpty(password)
    this.validationErrorMessage.password[4].isError = !this.haveSpecialCharacters(password) && !this.isEmpty(password)
    
    return  !this.validationErrorMessage.password[0].isError && 
            !this.validationErrorMessage.password[1].isError &&
            !this.validationErrorMessage.password[2].isError &&
            !this.validationErrorMessage.password[3].isError &&
            !this.validationErrorMessage.password[4].isError 
  }

  validateConfirmPassword(){
    let password = this.form.get('password').value
    let conFpassword = this.form.get('confPass').value
    this.validationErrorMessage.confPass[0].isError = !(password === conFpassword) && !this.isEmpty(conFpassword)
    return !this.validationErrorMessage.confPass[0].isError
  }
  
  validateFormAndSubmit(){
    if(!this.validateUserName())
      document.getElementById("username").focus();
    else if(!this.validateEmail())
      document.getElementById("email").focus();
    else if(!this.validatePhone())
      document.getElementById("phone").focus();
    else if(!this.validatePassword())
      document.getElementById("password").focus();
    else if(!this.validateConfirmPassword())
      document.getElementById("confPass").focus();
    else  
      this.submit();
  }

}
