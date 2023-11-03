import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Performance} from "../models/performance/Performance";
import {environment} from "../../environment/environment";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class PerformanceService {

  constructor(private http: HttpClient,
              private cookie: CookieService) { }

  getAllPerformance(page: number) {
    return this.http.get<Performance[]>(environment.backendURL + "/performances?page=" + page + "&size=5", {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  addNewPerformance(per: Performance) {
    const body = JSON.stringify(per);
    return this.http.post<Performance>(environment.backendURL + "/performances", body, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  getPerformanceById(id: number) {
    return this.http.get<Performance>(environment.backendURL + "/performances/" + id, {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  deleteActorFromPerformanceQueue(actorId: number, perforId: number) {
    return this.http.delete<void>(environment.backendURL + "/performances/" + perforId + "/actor/" + actorId, {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  // updatePerformance(performance: Performance) {
  //   const body = JSON.stringify(performance);
  //   return this.http.put<Performance>(environment.backendURL + "/performances/" + performance.id, body, {
  //     headers: {
  //       "Content-Type": "application/json"
  //     }});
  // }

  addActorsToPerformance(performance: Performance) {
    const body = JSON.stringify(performance.actorIds);
    return this.http.put<Performance>(environment.backendURL + "/performances/actors/" + performance.id, body, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }

  deletePerformance(id: number) {
    return this.http.delete<void>(environment.backendURL + "/performances/" + id, {
      headers: {
        "Authorization": "Bearer " + this.cookie.get("jwt-token")
      }});
  }
}
