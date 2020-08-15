import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  getAllUsers(){
    let url = 'http://localhost:8080/user/all'
    return this.http.get<Observable<any>>(url)
  }
}
