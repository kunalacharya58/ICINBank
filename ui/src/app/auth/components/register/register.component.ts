import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form:FormGroup
  error =false
  errorMsg = ""
  constructor(private fb:FormBuilder, private http:HttpClient) {
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
    console.log(this.form.value)
    let data = this.form.value
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json; charset=UTF-8',
      }),
      //observe: 'response' as 'response',
    };
    if(!(this.form.get('confPass')===this.form.get('password'))){
      this.error = true
      this.errorMsg = "Password not same"
    } else{
      this.error = false
      this.errorMsg = ""
    }
    this.http.post('http://localhost:8080/register',data,httpOptions).subscribe(
      (resp) => {console.log(resp)},
      (err) => {console.log(err.status)}
    )
   }
  ngOnInit(): void {
  }

}
