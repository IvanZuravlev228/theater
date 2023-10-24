import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Performance} from "../models/performance/Performance";
import {environment} from "../../environment/environment";

@Injectable({
  providedIn: 'root'
})
export class PerformanceService {

  constructor(private http: HttpClient) { }

  getAllPerformance(page: number) {
    return this.http.get<Performance[]>(environment.backendURL + "/performances?page=" + page + "&size=5", {
      headers: {

      }});
  }

  addNewPerformance(per: Performance) {
    const body = JSON.stringify(per);
    return this.http.post<Performance>(environment.backendURL + "/performances", body, {
      headers: {
        "Content-Type": "application/json"
      }});
  }

  getPerformanceById(id: number) {
    return this.http.get<Performance>(environment.backendURL + "/performances/" + id, {
      headers: {
      }});
  }

  deleteActorFromPerformanceQueue(actorId: number, perforId: number) {
    return this.http.delete<void>(environment.backendURL + "/performances/" + perforId + "/actor/" + actorId, {
      headers: {
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
        "Content-Type": "application/json"
      }});
  }

  deletePerformance(id: number) {
    return this.http.delete<void>(environment.backendURL + "/performances/" + id, {
      headers: {
      }});
  }
}
