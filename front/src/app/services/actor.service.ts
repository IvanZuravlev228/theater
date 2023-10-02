import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Actor} from "../models/Actor";
import {environment} from "../../environment/environment";

@Injectable({
  providedIn: 'root'
})
export class ActorService {
  constructor(private http: HttpClient) { }

  getAllActors() {
    return this.http.get<Actor[]>(environment.backendURL + "/actors", {
      headers: {
      }});
  }
}
