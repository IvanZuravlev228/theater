import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Actor} from "../models/actor/Actor";
import {environment} from "../../environment/environment";
import {ActorRegister} from "../models/actor/ActorRegister";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class ActorService {
  constructor(private http: HttpClient,
              private cookie: CookieService) { }

  getAllActors(page: number) {
    if (page === - 1) {
      return this.http.get<Actor[]>(environment.backendURL + "/actors", {
        headers: {
          "Authorization": "Bearer " + this.cookie.get("jwt-token")
        }});
    }
    return this.http.get<Actor[]>(environment.backendURL + "/actors?page=" + page + "&size=5", {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  saveNewActor(actor: ActorRegister) {
    const body = JSON.stringify(actor);
    return this.http.post<Actor>(environment.backendURL + "/actors", body, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  deleteActorById(actorId: number) {
    return this.http.delete<void>(environment.backendURL + "/actors/" + actorId, {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  getAllActorsByPerformanceId(perId: number) {
    return this.http.get<Actor[]>(environment.backendURL + "/actors/performance/" + perId, {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  getAllActorsWithoutContractByPerformanceId(perId: number) {
    return this.http.get<Actor[]>(environment.backendURL + "/actors/performance/" + perId + "?hasContracts=false", {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  getActorById(id: number) {
    return this.http.get<Actor>(environment.backendURL + "/actors/" + id, {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }
}
