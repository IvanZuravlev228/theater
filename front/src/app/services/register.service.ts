import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterUser} from "../models/user/RegisterUser";
import {User} from "../models/user/User";
import {environment} from "../../environment/environment";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  constructor(private http: HttpClient) { }

  registerUser(user: RegisterUser) {
    const body = JSON.stringify(user);
    return this.http.post<User>(environment.backendURL + "/users/register", body, {
      headers: {
        "Content-Type": "application/json"
      }});
  }
}
