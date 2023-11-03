import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginUser} from "../models/user/LoginUser";
import {environment} from "../../environment/environment";
import {Token} from "../models/token/Token";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient) { }

  loginUser(user: LoginUser) {
    const body = JSON.stringify(user);
    return this.http.post<Token>(environment.backendURL + "/users/login", body, {
      headers: {
        "Content-Type": "application/json"
      }});
  }
}
