import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':'application/json; charset=UTF-8',
    }),
    observe: 'response' as 'response',
  };

  constructor(private http:HttpClient) { }

  getAllUsers(){
    let url = 'http://localhost:8080/user/all'
    return this.http.get<Observable<any>>(url)
  }

  login(data){
    data = JSON.stringify(data)
    let url = 'http://localhost:8080/admin/login'
    return this.http.post(url,data,this.httpOptions)
  }

  logout(){
    sessionStorage.removeItem('adminId')
  }
}
