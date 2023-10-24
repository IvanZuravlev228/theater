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
  actorsForNewPer: Actor[] = [];
  actorsForUpdatePer: Actor[] = [];
  showAddFormBoolean: boolean = false;
  showCreateNewPerforBoolen: boolean = false;
  mockActorsIndexPage: number = -1;
  indexPage: number = 0;

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
    this.perforService.getAllPerformance(this.indexPage).subscribe({
      next: (performances) => {
        if (performances.length === 0) {
          this.indexPage--;
          return;
        }
        this.performances = performances;
      },
      error: (error) => {
        this.messageService.showMessage(error);
      }
    })
  }

  nextPage() {
    this.indexPage++;
    this.getAllPerformance();
    this.showAddFormBoolean = false;
    this.showCreateNewPerforBoolen = false;
  }

  previousPage() {
    if (this.indexPage !== 0) {
      this.indexPage--;
      this.getAllPerformance();
      this.showAddFormBoolean = false;
      this.showCreateNewPerforBoolen = false;
    }
  }

  // showActors(perId: number) {
  //   this.actorService.getAllActorsByPerformanceId(perId).subscribe({
  //     next: (actors) => {
  //       this.actorsByPerformance = actors;
  //     },
  //     error: (error) => {
  //       this.messageService.showMessage(error);
  //     }
  //   })
  // }

  addNewPerformance() {
    if (this.checkCorrectDataForPerformanceAdd(this.performanceForAdd)) {
      this.messageService.showMessage("Incorrect data fro new performance. Name length should be greater then 3, description then 3, budget then 0");
      return;
    }
    this.performanceForAdd.actorIds = this.actorsForNewPer.filter(actor => actor.selected).map(actor => actor.id);
    this.perforService.addNewPerformance(this.performanceForAdd).subscribe({
      next: (performance) => {
        this.performances.push(performance);
        this.performanceForAdd = new Performance();
        this.actorsForNewPer = this.actorsForNewPer.filter(actor => !actor.selected);
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
    this.actorService.getAllActors(this.mockActorsIndexPage).subscribe({
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
    this.actorsForUpdatePer = this.actorsForUpdatePer.filter(actor => !per.actorIds.includes(actor.id));
    if (this.showCreateNewPerforBoolen) {
      this.showCreateNewPerforBoolen = false;
    }
    this.showAddFormBoolean = true;
  }

  private resetCheckboxSelection() {
    this.actorsForNewPer.forEach(actor => {
      actor.selected = false;
    });
  }

  private checkCorrectDataForPerformanceAdd(performance: Performance) : boolean {
    return performance.name.length < 3 || performance.description.length < 3 || performance.budget < 0;
  }

  showCreatenewPerfor() {
    if (this.showAddFormBoolean) {
      this.showAddFormBoolean = false;
    }
    this.showCreateNewPerforBoolen = !this.showCreateNewPerforBoolen;
  }
}
