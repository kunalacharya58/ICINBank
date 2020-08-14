import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http:HttpClient) { }

  getPrimaryBalance(){
    let userId = localStorage.getItem('userId')
    let url = "http://localhost:8080/transaction/savings/"+userId
    return this.http.get(url)
  }

}
