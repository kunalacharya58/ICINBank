import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin.service';
import { of } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-admin-chequebook',
  templateUrl: './admin-chequebook.component.html',
  styleUrls: ['./admin-chequebook.component.css']
})
export class AdminChequebookComponent implements OnInit {

  checkBooks = []
  users = []
  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.getData();
  }

  getData(){
    this.getUsers();
    this.getCB();
  }

  getCB(){
    this.adminService.getCheckBookRequests().subscribe(
      (resp) => {
        resp.forEach((cb)=>{
          cb.username = this.getUserName(cb.requestedBy)
          this.getAccountNumber(cb)
          this.checkBooks.push(cb);
      })},
      (err) => {console.log(err)}
    )
  }

  getUsers(){
    this.adminService.getAllUsers().subscribe(
      (resp) => {
        resp.forEach((u)=>{
        this.users.push(u);
      })},
      (err) => {console.log(err)}
    )
  }

  getUserName(id : number):string {
    return this.users.find( u => u.id === id ).username;
  }

  getAccountNumber(cb){
    
    if(cb.accountType === "Primary"){
      this.adminService.getPrimaryAccount(cb.requestedBy).subscribe(
        res => { 
          cb.accountNumber =res.number
        },
        err =>{
          console.log(err)
        }
      )
    }
    else{
      this.adminService.getSavingsAccount (cb.requestedBy).subscribe(
        res => { 
          cb.accountNumber =res.number
        },
        err =>{
          console.log(err)
        }
      )
    }
  }


  approveCB(id : number){
    this.adminService.confirmRequest(id).subscribe(
      res =>{
        console.log(res)
        this.checkBooks.find( cb => cb.id == id ).confirmed = true
      },
      (err: HttpErrorResponse) =>{
        console.log(err)
      }
    );
  }

}
