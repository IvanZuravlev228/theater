import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import { ActorComponent } from './components/actor/actor.component';
import {PerformanceComponent} from "./components/performance/performance.component";
import {ContractComponent} from "./components/contract/contract.component";
import {TheaterComponent} from "./components/theater/theater.component";
import {environment} from "../environment/environment";

const routes: Routes = [
  {path: '', component: AppComponent},
  {path: environment.rootURL + "/actors", component: ActorComponent},
  {path: environment.rootURL + "/performances", component: PerformanceComponent},
  {path: environment.rootURL + "/contracts", component: ContractComponent},
  {path: environment.rootURL, component: TheaterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
