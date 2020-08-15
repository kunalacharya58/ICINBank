import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin.service'
@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  users = []

  constructor(private adserve:AdminService) { }

  ngOnInit(): void {
    this.getAll()
  }


  getAll(){
    this.adserve.getAllUsers().subscribe(
      (resp) => {
        resp.forEach((u)=>{
        this.users.push(u);
      })},
      (err) => {console.log(err)}
    )
  }

}
