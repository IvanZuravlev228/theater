import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PerformanceService} from "../../services/performance.service";
import {Performance} from "../../models/performance/Performance";
import {ActorService} from "../../services/actor.service";
import {Actor} from "../../models/actor/Actor";
import {MessageService} from "../../services/message.service";
import {ActorWithContract} from "../../models/actor/ActorWithContract";
import {ContractService} from "../../services/contract.service";
import {Contract} from "../../models/contract/Contract";
import {environment} from "../../../environment/environment";

@Component({
  selector: 'app-performance-details',
  templateUrl: './performance-details.component.html',
  styleUrls: ['./performance-details.component.css']
})
export class PerformanceDetailsComponent implements OnInit {
  mainPerformance: Performance = new Performance();
  actorWithContracts: ActorWithContract[] = [];
  performanceId: number = -1;

  constructor(private activatedRoute: ActivatedRoute,
              private performanceService: PerformanceService,
              private actorService: ActorService,
              private messageService: MessageService,
              private contractService: ContractService) {
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.performanceId = params['perId'];
      this.getPerformanceById(this.performanceId);
      this.getActorsByPerformanceId(this.performanceId);
    })
  }

  getPerformanceById(id: number) {
      this.performanceService.getPerformanceById(id).subscribe({
        next: (per) => {
          this.mainPerformance = per;
        },
        error: () => {
        }
      });
  }

  getActorsByPerformanceId(perId: number) {
    this.actorService.getAllActorsByPerformanceId(perId).subscribe({
      next: (actors: Actor[]) => {
        actors.forEach(actor => {
            const actorWithContract: ActorWithContract = {
              actor: actor,
              contract: new Contract()
            };
            this.actorWithContracts.push(actorWithContract);
            this.getContractByActorId(actorWithContract);
        });
      },
      error: (error) => {
        this.messageService.showMessage(error);
      }
    });
  }

  getContractByActorId(ac: ActorWithContract) {
    this.contractService.getContractByActorAndPerformanceId(ac.actor.id, this.performanceId).subscribe({
      next: (contract) => {
        ac.contract = contract;
        this.mainPerformance.budget = this.mainPerformance.budget - contract.salary;
      },
      error: (error) => {
        this.messageService.showMessage("May be this actor doesn't have a contract");
      }
    });
  }
}
