import { Component } from '@angular/core';
import {RegisterService} from "../../services/register.service";
import {RegisterUser} from "../../models/user/RegisterUser";
import {Router} from "@angular/router";
import {environment} from "../../../environment/environment";
import {MessageService} from "../../services/message.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerUser: RegisterUser = new RegisterUser();

  constructor(private registerService: RegisterService,
              private messageService: MessageService,
              private router: Router) {}

  register() {
    this.registerService.registerUser(this.registerUser).subscribe({
      next: (user) => {
        this.registerUser = new RegisterUser();
        this.router.navigate([environment.rootURL + "/sign-in"]);
      },
      error: (error) => {
        this.errorHandle(error);
      }
    })
  }

  private errorHandle(error: any) {
    if (error.status === 0) {
      this.messageService.showMessage("You don't have access or you have to re authenticated");
    }
    if (error.status === 400) {
      this.messageService.showMessage("Something went wrong");
    }
  }
}
