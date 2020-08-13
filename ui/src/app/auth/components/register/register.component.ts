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

}
