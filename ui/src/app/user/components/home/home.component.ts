import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../account.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private ac:AccountService) { }

  ngOnInit(): void {
    console.log(this.ac.getPrimaryBalance().subscribe(
      (resp) => { console.log(resp) },
      (err) => { console.log(err) }
    ))
  }


}
