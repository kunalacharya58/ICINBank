import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from '../../admin.service';

@Component({
  selector: 'app-admin-base',
  templateUrl: './admin-base.component.html',
  styleUrls: ['./admin-base.component.css']
})
export class AdminBaseComponent implements OnInit {

  constructor(private adserve:AdminService,private router:Router, private route:ActivatedRoute) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('adminId') === ''|| sessionStorage.getItem('adminId')===null)
      this.router.navigate(['admin-login'])
  }

  logout(){
    this.adserve.logout();
    location.reload()
  }

}
