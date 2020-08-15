import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  getSavingsBalance() {
    let userId = localStorage.getItem('userId')
    let url = "http://localhost:8080/savings/"+userId
    return this.http.get<any>(url)
  }



  constructor(private http:HttpClient) { }

  getSavingsTransaction(){
    let userId = localStorage.getItem('userId')
    let url = "http://localhost:8080/transaction/savings/"+userId
    return this.http.get<Observable<any>>(url)
  }

  getPrimaryTransaction(){
    let userId = localStorage.getItem('userId')
    let url = "http://localhost:8080/transaction/primary/"+userId
    return this.http.get<Observable<any>>(url)
  }

  getPrimaryBalance(): any{
    let userId = localStorage.getItem('userId')
    let url = "http://localhost:8080/primary/"+userId
    return this.http.get<any>(url)
  }

  sendSelf(data) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json; charset=UTF-8',
      }),
      observe: 'response' as 'response',
    };
    console.log(data)
    let url = "http://localhost:8080/account/exchange"
    return this.http.post(url,data,httpOptions)
  }

}
