import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

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

}
