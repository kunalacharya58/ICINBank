import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AdminService } from '../../admin.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  form: FormGroup;
  usernameError = false;
  usernameErrorMessage = 'Username should contain only alphabets or numbers';

  passError = false;
  passErrorMessage = 'must contain atleast 6 characters';

  error=false
  errMsg = ''

  constructor(private fb: FormBuilder, private adserve: AdminService, private router:Router) {
    this.form = this.fb.group(
      { username: [''], password: [''] }
    );
  }

  ngOnInit(): void {
    if (sessionStorage.getItem('adminId') == '' || sessionStorage.getItem('adminId') == null) {
    } else {
      this.router.navigate(['admin']);
    }
  }

  haveSpecialCharacters(str: string) {
    let regex = new RegExp(/^[0-9a-zA-Z]+$/);
    return !regex.test(str);
  }

  isEmpty(str: string) {
    return !str || str.length === 0 || str.length === undefined;
  }


  validateUserName() {
    let username = this.form.get('username').value;
    this.usernameError =
      this.haveSpecialCharacters(username) && !this.isEmpty(username);
    return !this.usernameError;
  }

  validatePassword() {
    let password = this.form.get('password').value;
    this.passError = password.length < 6 && !this.isEmpty(password);
    return !this.passError;
  }

  validateFormAndSubmit() {
    if (!this.validateUserName()) document.getElementById('lCustId').focus();
    else if (!this.validatePassword()) document.getElementById('lpass').focus();
    else {
      this.submit();
    }
  }

  submit() {
    this.adserve.login(this.form.value).subscribe(
      (resp) => {
        if (resp.ok) {
          sessionStorage.setItem('adminId', resp.headers.get('adminID'));
          this.router.navigate(['admin']);
        }
      },
      (err: HttpErrorResponse) => {
        this.errMsg = err.headers.get('message');
        this.error = true;
      }
    );
  }
}
