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
              private router: Router) {
  }

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
        console.log(error.error.message);
        this.errorHandle(error.status);
      }
    })
  }

  private checkCorrectLoginUser(user: LoginUser) {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    console.log(emailPattern.test(user.email) && user.password.length > 5);
    return (emailPattern.test(user.email) && user.password.length > 5);
  }

  private errorHandle(status: number) {
    if (status === 511) {
      this.messageService.showMessage("You should re authenticate");
    }
    if (status === 403) {
      this.messageService.showMessage("Something went wrong. Try again please")
    }
    if (status === 400) {
      this.messageService.showMessage("You may have entered an incorrect password or email address")
    }
  }
}
