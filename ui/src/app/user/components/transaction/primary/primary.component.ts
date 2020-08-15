import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/user/account.service';

@Component({
  selector: 'app-primary',
  templateUrl: './primary.component.html',
  styleUrls: ['./primary.component.css'],
})
export class PrimaryComponent implements OnInit {
  constructor(private acc: AccountService) {}
  transactions = [];

  ngOnInit(): void {
    this.acc.getPrimaryTransaction().subscribe((resp) => {
      resp.forEach((item) => {
        console.log(item)
        this.transactions.push(item);
      });
    });
  }
}
