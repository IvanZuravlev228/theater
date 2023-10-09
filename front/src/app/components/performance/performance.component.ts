import {Component, OnInit} from '@angular/core';
import {Performance} from "../../models/performance/Performance";
import {PerformanceService} from "../../services/performance.service";
import {MessageService} from "../../services/message.service";
import {ActorService} from "../../services/actor.service";
import {Actor} from "../../models/actor/Actor";
import {Router} from "@angular/router";
import {environment} from "../../../environment/environment";

@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.css']
})
export class PerformanceComponent implements OnInit{
  performances: Performance[] = [];
  performanceFroUpdate: Performance = new Performance()
  performanceForAdd: Performance = new Performance();
  actorsByPerformance: Actor[] = [];
  actorsForNewPer: Actor[] = [];
  actorsForUpdatePer: Actor[] = [];

  constructor(private perforService: PerformanceService,
              private actorService: ActorService,
              private messageService: MessageService,
              private router: Router) {
  }

  ngOnInit() {
    this.getAllPerformance();
    this.getAllActors();
  }

  getAllPerformance() {
    this.perforService.getAllPerformance().subscribe({
      next: (performances) => {
        this.performances = performances;
      },
      error: (error) => {
        this.messageService.showMessage(error);
      }
    })
  }

  showActors(perId: number) {
    this.actorService.getAllActorsByPerformanceId(perId).subscribe({
      next: (actors) => {
        this.actorsByPerformance = actors;
      },
      error: (error) => {
        this.messageService.showMessage(error);
      }
    })
  }

  addNewPerformance() {
    this.performanceForAdd.actorIds = this.actorsForNewPer.filter(actor => actor.selected).map(actor => actor.id);
    this.perforService.addNewPerformance(this.performanceForAdd).subscribe({
      next: (performance) => {
        this.performances.push(performance);
      },
      error: (error) => {
        this.messageService.showMessage(error);
      }
    })
  }

  showMore(perforId: number) {
    this.router.navigate([environment.rootURL + "/performances/details"], {queryParams: {perId: perforId}});
  }

  getAllActors() {
    this.actorService.getAllActors().subscribe({
      next: (actors) => {
        this.actorsForNewPer = actors;
      },
      error: (error) => {
        this.messageService.showMessage(error);
      }
    });
  }

  addActorsToPerfor() {
    this.performanceFroUpdate.actorIds = this.actorsForNewPer.filter(ac => ac.selected).map(ac => ac.id);
    this.perforService.addActorsToPerformance(this.performanceFroUpdate).subscribe({
      next: (performance) => {
        const index = this.performances.findIndex(p => p.id === performance.id);
        if (index !== -1) {
          this.performances.splice(index, 1);
          this.performances.push(performance);
          this.resetCheckboxSelection();
        }
      },
      error: (error) => {
        this.messageService.showMessage(error);
      }
    })
  }

  showAddForm(per: Performance) {
    this.performanceFroUpdate = per;
    this.actorsForUpdatePer = this.actorsForNewPer;
    // this.actorsForNewPer
    // per.actorIds
    this.actorsForUpdatePer = this.actorsForUpdatePer.filter(actor => !per.actorIds.includes(actor.id));

  }

  private resetCheckboxSelection() {
    this.actorsForNewPer.forEach(actor => {
      actor.selected = false;
    });
  }
}
