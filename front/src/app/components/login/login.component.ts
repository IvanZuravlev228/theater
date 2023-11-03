import { Component } from '@angular/core';
import {RegisterUser} from "../../models/user/RegisterUser";
import {LoginUser} from "../../models/user/LoginUser";
import {LoginService} from "../../services/login.service";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {environment} from "../../../environment/environment";
import {MessageService} from "../../services/message.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', '../register/register.component.css']
})
export class LoginComponent {
  loginUser: LoginUser = new LoginUser();

  constructor(private loginService: LoginService,
              private messageService: MessageService,
              private cookie: CookieService,
              private router: Router) {}

  login() {
    if (!this.checkCorrectLoginUser(this.loginUser)) {
      this.messageService.showMessage("Wrong email address or password too simple")
      return;
    }

    this.loginService.loginUser(this.loginUser).subscribe({
      next: (token) => {
        this.cookie.set("jwt-token", token.token);
        this.router.navigate([environment.rootURL + "/performances"])
      },
      error: (error) => {
        this.errorHandle(error);
      }
    })
  }

  private checkCorrectLoginUser(user: LoginUser) {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return (emailPattern.test(user.email) && user.password.length > 5);
  }

  private errorHandle(error: any) {
    if (error.status === 511) {
      this.messageService.showMessage("You should re authenticate");
    }
    else if (error.status === 403) {
      this.messageService.showMessage("Something went wrong. Try again please")
    }
    else {
      this.messageService.showMessage(error.error.message);
    }

  }
}
