import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Prize} from "../models/prize/Prize";
import {environment} from "../../environment/environment";
import {PrizesIdsRequestDto} from "../models/prize/PrizesIdsRequestDto";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class PrizeService {
  constructor(private http: HttpClient,
              private cookie: CookieService) { }

  getAllPrizesByIds(dto: PrizesIdsRequestDto) {
    const body = JSON.stringify(dto);
    return this.http.post<Prize[]>(environment.backendURL + "/prizes/by-actor", body, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }})
  }

  getAllPrizes() {
    return this.http.get<Prize[]>(environment.backendURL + "/prizes", {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }
}
