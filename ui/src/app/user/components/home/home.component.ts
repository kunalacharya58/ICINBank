import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../account.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private ac:AccountService) { }

  primary : any 
  savings : any
  ngOnInit(): void {
    console.log(this.ac.getPrimaryBalance().subscribe(
      (resp) => { 
        console.log(resp);
        this.primary = resp
       },
      (err) => { console.log(err) }
    ))

    console.log(this.ac.getSavingsBalance().subscribe(
      (resp) => { 
        console.log(resp);
        this.savings = resp
       },
      (err) => { console.log(err) }
    ))
  }


}
