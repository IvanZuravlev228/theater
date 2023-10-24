import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Contract} from "../models/contract/Contract";
import {environment} from "../../environment/environment";
import {Actor} from "../models/actor/Actor";

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  constructor(private http: HttpClient) { }

  getContractById(id: number) {
    return this.http.get<Contract>(environment.backendURL + "/contracts/" + id, {
      headers: {

      }});
  }

  getContractByActorAndPerformanceId(actorId: number, perId: number) {
    return this.http.get<Contract>(environment.backendURL + "/contracts/actor/" + actorId + "/performance/" + perId, {
      headers: {

      }});
  }

  saveNewContract(contract: Contract) {
    const body = JSON.stringify(contract);
    return this.http.post<Contract>(environment.backendURL + "/contracts", body, {
      headers: {
        "Content-Type": "application/json"
      }});
  }

  deleteContract(contractId: number) {
    return this.http.delete<void>(environment.backendURL + "/contracts/" + contractId, {
      headers: {

      }});
  }
}
