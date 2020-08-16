import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/user/account.service';

@Component({
  selector: 'app-savings',
  templateUrl: './savings.component.html',
  styleUrls: ['./savings.component.css']
})
export class SavingsComponent implements OnInit {

  constructor(private acc:AccountService) { }
  transactions = [];

  ngOnInit(): void {
    this.acc.getSavingsTransaction().subscribe((resp) => {
      resp.forEach((item) => {
        this.transactions.push(item);
      });
    });
  }

}
