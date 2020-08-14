import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':'application/json; charset=UTF-8',
    }),
    observe: 'response' as 'response',
  };

  deposit(data:any){
    let url = 'http://localhost:8080/account/deposit'
    console.log(data)
    return this.http.post<HttpResponse<any>>(url,data,this.httpOptions)
  }
}
