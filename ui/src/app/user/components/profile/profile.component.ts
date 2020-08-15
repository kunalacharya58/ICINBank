import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from '../../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css', '../base/base.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private userService : UserService, private router : Router) { }

  public userDetails : User;

  emailError = {
    error : false,
    message : "enter valid e-mail"
  }

  addressError = {
    error : false,
    message : "address cannot be empty"
  }

  passwordError = [
    { message : "must contain atleast 6 characters", error : false },
    { message : "must contain atleast one number" , error : false},
    { message : "must contain atleast one uppercase character", error : false },
    { message : "must contain atleast one lowercase character" , error : false},
    { message : "must contain atleast one special character", error : false }
  ]

  confPassError = {
    message : "passwords do not match", error : false
  }

  success = {
    message : "new profile details are updated", boolean : false
  }

  newPassword = ''
  newEmail = ''
  newAddress = ''
  confPass = ''

  submit(){

    this.userService.updateUser(this.userDetails).subscribe(
      res=>{
        console.log(res)
        this.success.boolean = true;
        this.newPassword = this.newEmail = this.newAddress = this.confPass = ''
      }
    );
  }
  ngOnInit(): void {
    console.log(this.userService.getUser().subscribe(
      resp => {
        console.log(resp)
        this.userDetails = resp
      },
      (err) => { console.log(err) }
    ))
  }

  isEmpty(str : string){
    return (!str || str.length === 0 || str.length === undefined)
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

  validateEmail(){

    this.success.boolean = false

    let regex = new RegExp(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/);
    this.emailError.error = !this.isEmpty(this.newEmail) && !regex.test(this.newEmail)
    return !this.emailError.error
  }

  validatePassword(){

    this.success.boolean = false

    this.passwordError[0].error = this.newPassword.length < 6 && !this.isEmpty(this.newPassword)
    this.passwordError[1].error = !this.haveNumber(this.newPassword) && !this.isEmpty(this.newPassword)
    this.passwordError[2].error = !this.haveUpper(this.newPassword) && !this.isEmpty(this.newPassword)
    this.passwordError[3].error = !this.haveLower(this.newPassword) && !this.isEmpty(this.newPassword)
    this.passwordError[4].error = !this.haveSpecialCharacters(this.newPassword) && !this.isEmpty(this.newPassword)

    return  !this.passwordError[0].error &&
            !this.passwordError[1].error &&
            !this.passwordError[2].error &&
            !this.passwordError[3].error &&
            !this.passwordError[4].error
  }

  validateConfirmPassword(){

    this.success.boolean =false

    this.confPassError.error = !(this.newPassword === this.confPass) && !this.isEmpty(this.confPass)
    return !this.confPassError.error
  }

  validateAndSubmit(){
    if(!this.validateEmail())
      document.getElementById("email").focus();
    else if(!this.validatePassword())
      document.getElementById("password").focus();
    else if(!this.validateConfirmPassword())
      document.getElementById("confPass").focus();
    else{
      if(!this.isEmpty(this.newEmail))
        this.userDetails.email = this.newEmail
      if(!this.isEmpty(this.newAddress))
        this.userDetails.address = this.newAddress
      if(!this.isEmpty(this.newPassword))
        this.userDetails.password = this.newPassword
      if(this.isEmpty(this.newEmail) && this.isEmpty(this.newPassword) && this.isEmpty(this.newAddress)){

      }
      else
        this.submit();
    }
  }

}
