import {Component, OnInit} from '@angular/core';
import {Performance} from "../../models/performance/Performance";
import {PerformanceService} from "../../services/performance.service";
import {ContractService} from "../../services/contract.service";
import {Actor} from "../../models/actor/Actor";
import {ActorService} from "../../services/actor.service";
import {ActorWithContract} from "../../models/actor/ActorWithContract";
import {MessageService} from "../../services/message.service";
import {Contract} from "../../models/contract/Contract";
import {PerformanceActorContract} from "../../models/performance/PerformanceActorContract";
import {Router} from "@angular/router";
import {environment} from "../../../environment/environment";

@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
  styleUrls: ['./contract.component.css']
})
export class ContractComponent implements OnInit{
  // performances: Performance[] = [];
  // actorWithContracts: ActorWithContract[] = [];
  // actorWithoutContracts: Actor[] = [];

  performanceActorContract: PerformanceActorContract[] = [];

  constructor(private performanceService: PerformanceService,
              private contractService: ContractService,
              private actorService: ActorService,
              private messageService:MessageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllPerformance();
  }

  getAllPerformance() {
    this.performanceService.getAllPerformance().subscribe({
      next: (performances ) => {
        performances.forEach(performance => {
          const perActCont: PerformanceActorContract = {
            performance: performance,
            actorsWithoutContract: [],
            actorWithContract: []
          };
          this.performanceActorContract.push(perActCont);
        })
      },
      error: (error) => {
        console.log(error);
      }
    })
  }


  getContractInfo(pac: PerformanceActorContract) {
    this.actorService.getAllActorsWithoutContractByPerformanceId(pac.performance.id).subscribe({
      next: (actors ) => {
        // this.actorWithoutContracts = actors;
        pac.actorsWithoutContract = actors;
      },
      error: (error) => {
        console.log(error);
      }
    });

    this.actorService.getAllActorsByPerformanceId(pac.performance.id).subscribe({
      next: (actors ) => {
        actors.forEach(actor => {
          const existingActor = pac.actorWithContract.find(a => a.actor.id === actor.id);
          console.log(!existingActor);
          if (!existingActor) {
            const actorWithContract: ActorWithContract = {
              actor: actor,
              contract: new Contract()
            };
            pac.actorWithContract.push(actorWithContract);
          }
        })
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  getContractByActorAndPerforId(ac: ActorWithContract, performanceId: number) {
    this.contractService.getContractByActorAndPerformanceId(ac.actor.id, performanceId).subscribe({
      next: (contract) => {
        console.log(contract);
        ac.contract = contract;
      },
      error: (error) => {
        this.messageService.showMessage("May be this actor doesn't have a contract");
      }
    });
  }

  signTheActor(perforId: number, actorId: number, prizesIds: number[]) {
    this.router.navigate([environment.rootURL + "/contracts/details"], {
      queryParams: {
        perforId: perforId,
        actorId: actorId,
        prizesIds: prizesIds
      }});
  }

  breakContract(contractId: number, actorWithContract: ActorWithContract[], actorsWithoutContract: Actor[]) {
    this.contractService.deleteContract(contractId).subscribe({
      next: () => {
        const index = actorWithContract.findIndex(ac => ac.contract.id === contractId);

        // Если актер найден (index не равен -1), удаляем его из массива
        if (index !== -1) {
          actorsWithoutContract.push(actorWithContract[index].actor);
          actorWithContract.splice(index, 1);
        }
      },
      error: (error) => {
        this.messageService.showMessage("May be this actor doesn't have a contract");
      }
    })
  }

  deleteActorFromPerformanceQueue(actorId: number, perforId: number, actorsWithoutContract: Actor[]) {
    this.performanceService.deleteActorFromPerformanceQueue(actorId, perforId).subscribe({
      next: () => {
        const index = actorsWithoutContract.findIndex(actor => actor.id === actorId);

        // Если актер найден (index не равен -1), удаляем его из массива
        if (index !== -1) {
          actorsWithoutContract.splice(index, 1);
        }
      },
      error: (error) => {
        this.messageService.showMessage("May be this actor doesn't have a contract");
      }
    })
  }
}
