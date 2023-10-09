import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { ActorComponent } from './components/actor/actor.component';
import { HeaderComponent } from './components/header/header.component';
import { PerformanceComponent } from './components/performance/performance.component';
import { ContractComponent } from './components/contract/contract.component';
import { TheaterComponent } from './components/theater/theater.component';
import { MessageComponent } from './components/message/message.component';
import { PerformanceDetailsComponent } from './components/performance-details/performance-details.component';
import { ContractDetailsComponent } from './components/contract-details/contract-details.component';

@NgModule({
  declarations: [
    AppComponent,
    ActorComponent,
    HeaderComponent,
    PerformanceComponent,
    ContractComponent,
    TheaterComponent,
    MessageComponent,
    PerformanceDetailsComponent,
    ContractDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
