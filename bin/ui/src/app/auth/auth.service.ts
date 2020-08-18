import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }


  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':'application/json; charset=UTF-8',
    }),
    observe: 'response' as 'response',
  };


  login(data:any){
    let url = 'https://localhost:8080/login'
    data = JSON.stringify(data)
    return this.http.post<HttpResponse<any>>(url,data,this.httpOptions)
  }

  register(data:any){
    let url = 'https://localhost:8080/register'
    data = JSON.stringify(data)
    return this.http.post<HttpResponse<any>>(url,data,this.httpOptions)
  }

  logout(){
    sessionStorage.removeItem('userId')
  }

  disableUser( username : string ) {
    let url = 'https://localhost:8080/user/disable/username/'+username
    return this.http.post(url,null,this.httpOptions)
  }
}
