import { Component, OnInit } from '@angular/core';
import { FormGroup,FormBuilder } from "@angular/forms";
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse, HttpHeaderResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form:FormGroup
  success = false
  constructor(public fb:FormBuilder, private http:HttpClient) {
    this.form = this.fb.group({
      username: [''],
      password: [''],
    })
  }




  submit(){
    console.log(this.form.value)
    let data = JSON.stringify(this.form.value)
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json; charset=UTF-8',
      }),
      observe: 'response' as 'response',
    };
    this.http.post<HttpResponse<any>>('http://localhost:8080/login',data,httpOptions).subscribe(
      (resp) => {console.log(resp)},
      (err:HttpHeaderResponse) => {console.log(err.headers.get('message'))}
    )
  }
  ngOnInit(): void {
  }

}
