import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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

  getPrimaryBalance(): any{
    let userId = localStorage.getItem('userId')
    let url = "http://localhost:8080/primary/"+userId
    return this.http.get<any>(url)
  }

}
