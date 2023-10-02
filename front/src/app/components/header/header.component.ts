import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {environment} from "../../../environment/environment";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private router: Router) {
  }

  goToActorPage() {
    this.router.navigate([environment.rootURL + "/actors"])
  }

  goToPerformancePage() {
    this.router.navigate([environment.rootURL + "/performances"])
  }

  goToContracPage() {
    this.router.navigate([environment.rootURL + "/contracts"])
  }
}
