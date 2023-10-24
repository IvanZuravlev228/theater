import {Component, OnInit} from '@angular/core';
import {MessageService} from "../../services/message.service";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  message = "";
  hidden: boolean = false;
  title = "Error";

  constructor(private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.messageService.message$
      .subscribe(text => {
        this.hidden = true;
        this.message = text;
      })
  }
}
