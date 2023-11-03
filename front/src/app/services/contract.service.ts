import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Contract} from "../models/contract/Contract";
import {environment} from "../../environment/environment";
import {Actor} from "../models/actor/Actor";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  constructor(private http: HttpClient,
              private cookie: CookieService) { }

  // getContractById(id: number) {
  //   return this.http.get<Contract>(environment.backendURL + "/contracts/" + id, {
  //     headers: {
  //     }});
  // }

  getContractByActorAndPerformanceId(actorId: number, perId: number) {
    return this.http.get<Contract>(environment.backendURL + "/contracts/actor/" + actorId + "/performance/" + perId, {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  saveNewContract(contract: Contract) {
    const body = JSON.stringify(contract);
    return this.http.post<Contract>(environment.backendURL + "/contracts", body, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  deleteContract(contractId: number) {
    return this.http.delete<void>(environment.backendURL + "/contracts/" + contractId, {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }
}
