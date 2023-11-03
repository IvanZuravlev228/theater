import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import { ActorComponent } from './components/actor/actor.component';
import {PerformanceComponent} from "./components/performance/performance.component";
import {ContractComponent} from "./components/contract/contract.component";
import {TheaterComponent} from "./components/theater/theater.component";
import {environment} from "../environment/environment";
import {PerformanceDetailsComponent} from "./components/performance-details/performance-details.component";
import {ContractDetailsComponent} from "./components/contract-details/contract-details.component";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";

const routes: Routes = [
  {path: '', component: AppComponent},
  {path: environment.rootURL + "/actors", component: ActorComponent},
  {path: environment.rootURL + "/performances", component: PerformanceComponent},
  {path: environment.rootURL + "/performances/details", component: PerformanceDetailsComponent},
  {path: environment.rootURL + "/contracts", component: ContractComponent},
  {path: environment.rootURL + "/contracts/details", component: ContractDetailsComponent},
  {path: environment.rootURL, component: TheaterComponent},
  {path: environment.rootURL + "/sign-up", component: RegisterComponent},
  {path: environment.rootURL + "/sign-in", component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
