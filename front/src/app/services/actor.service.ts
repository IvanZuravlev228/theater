import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Actor} from "../models/actor/Actor";
import {environment} from "../../environment/environment";
import {ActorRegister} from "../models/actor/ActorRegister";

@Injectable({
  providedIn: 'root'
})
export class ActorService {
  constructor(private http: HttpClient) { }

  getAllActors(page: number) {
    if (page === - 1) {
      return this.http.get<Actor[]>(environment.backendURL + "/actors", {
        headers: {
        }});
    }
    return this.http.get<Actor[]>(environment.backendURL + "/actors?page=" + page + "&size=5", {
      headers: {
      }});
  }

  saveNewActor(actor: ActorRegister) {
    const body = JSON.stringify(actor);
    return this.http.post<Actor>(environment.backendURL + "/actors", body, {
      headers: {
        "Content-Type": "application/json"
      }});
  }

  deleteActorById(actorId: number) {
    return this.http.delete<void>(environment.backendURL + "/actors/" + actorId, {
      headers: {
      }});
  }

  getAllActorsByPerformanceId(perId: number) {
    return this.http.get<Actor[]>(environment.backendURL + "/actors/by-performance/" + perId, {
      headers: {
      }});
  }

  getAllActorsWithoutContractByPerformanceId(perId: number) {
    return this.http.get<Actor[]>(environment.backendURL + "/actors/by-performance/" + perId + "?hasContracts=false", {
      headers: {
      }});
  }

  getActorById(id: number) {
    return this.http.get<Actor>(environment.backendURL + "/actors/" + id, {
      headers: {
      }});
  }
}
