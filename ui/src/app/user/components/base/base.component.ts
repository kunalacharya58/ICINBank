import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css']
})
export class BaseComponent implements OnInit {

  constructor(private router:Router, private auth:AuthService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('userId') === ''|| sessionStorage.getItem('userId')===null)
      this.router.navigate(['login'])
  }

  logout(){
    this.auth.logout();
    location.reload()
  }
}
