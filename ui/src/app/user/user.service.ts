import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { User } from '../user';

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
    let url = 'http://localhost:8080/deposit'

    return this.http.post<HttpResponse<any>>(url,data,this.httpOptions)
  }

  withdraw(data:any){
    let url = 'http://localhost:8080/withdraw'

    return this.http.post<HttpResponse<any>>(url,data,this.httpOptions)
  }

  getUser():any{
    let userId = sessionStorage.getItem('userId')
    let url = "http://localhost:8080/user/"+userId
    return this.http.get<User>(url)
  }

  updateUser(user){

    let url = "http://localhost:8080/user/update"
    return this.http.put(url,user)
  }
}
