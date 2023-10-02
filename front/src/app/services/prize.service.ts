import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Prize} from "../models/prize/Prize";
import {environment} from "../../environment/environment";
import {PrizesIdsRequestDto} from "../models/prize/PrizesIdsRequestDto";

@Injectable({
  providedIn: 'root'
})
export class PrizeService {

  constructor(private http: HttpClient) { }

  getAllPrizesByIds(dto: PrizesIdsRequestDto) {

    const body = JSON.stringify(dto);
    console.log("ids for send: " + body);
    return this.http.post<Prize[]>(environment.backendURL + "/prizes/by-actor", body, {
      headers: {
        "Content-Type": "application/json"
      }})
  }
}
