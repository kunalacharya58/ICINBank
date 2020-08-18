import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin.service'
@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  users = []
  enabledUser = []
  disabledUser = []
  displayUser = []

  enabled = true
  disabled = true
  constructor(private adserve:AdminService) { }

  ngOnInit(): void {
    this.getAll()
    this.displayUser = this.users
  }


  getAll(){
    this.users =[]
    this.enabledUser = []
    this.disabledUser = []
    this.adserve.getAllUsers().subscribe(
      (resp) => {
        resp.forEach((u)=>{
        this.users.push(u);
        if(u.enabled)
          this.enabledUser.push(u)
        else
          this.disabledUser.push(u)
      })},
      (err) => {console.log(err)}
    )
  }

  userStatus(user){
    if(user.enabled)
      this.adserve.disableUser(user.id).subscribe(
        res=>{
          console.log(res)
        }
      )
    else(status)
      this.adserve.enableUser(user.id).subscribe(
        res=>{
          console.log(res)
        }
      )
   user.enabled = !user.enabled
  }
  filterUser(){
    this.getAll()
    if(this.enabled && this.disabled){
      this.displayUser = this.users
    }
    else if(this.enabled){
      this.displayUser = this.enabledUser
    }
    else if(this.disabled){
      this.displayUser = this.disabledUser
    }
    else{
      this.displayUser=[]
    }
  }
}


