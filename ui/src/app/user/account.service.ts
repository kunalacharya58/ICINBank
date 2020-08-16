import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JsonPipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http:HttpClient) { }

  getSavingsTransaction(){
    let userId = sessionStorage.getItem('userId')
    let url = "http://localhost:8080/transaction/savings/"+userId
    return this.http.get<Observable<any>>(url)
  }

  getPrimaryTransaction(){
    let userId = sessionStorage.getItem('userId')
    let url = "http://localhost:8080/transaction/primary/"+userId
    return this.http.get<Observable<any>>(url)
  }

  getPrimaryBalance(): any{
    let userId = sessionStorage.getItem('userId')
    let url = "http://localhost:8080/account/primary/"+userId
    return this.http.get<any>(url)
  }

  getSavingsBalance() {
    let userId = sessionStorage.getItem('userId')
    let url = "http://localhost:8080/account/savings/"+userId
    return this.http.get<any>(url)
  }

  sendSelf(data) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json; charset=UTF-8',
      }),
      observe: 'response' as 'response',
    };
    data = JSON.stringify(data)
    let url = "http://localhost:8080/account/exchange"
    return this.http.post(url,data,httpOptions)
  }

}
