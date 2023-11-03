import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {environment} from "../../../environment/environment";
import {PerformanceActorContract} from "../../models/performance/PerformanceActorContract";
import {PerformanceService} from "../../services/performance.service";
import {ContractService} from "../../services/contract.service";
import {ActorService} from "../../services/actor.service";
import {MessageService} from "../../services/message.service";
import {ActorWithContract} from "../../models/actor/ActorWithContract";
import {Contract} from "../../models/contract/Contract";
import {Actor} from "../../models/actor/Actor";

@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
  styleUrls: ['./contract.component.css']
})
export class ContractComponent implements OnInit{
  performanceActorContract: PerformanceActorContract[] = [];
  indexPage: number = 0;

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

    this.performanceService.getAllPerformance(this.indexPage).subscribe({
      next: (performances ) => {
        if (performances.length === 0) {
          this.indexPage--;
          return;
        }
        this.performanceActorContract.splice(0);
        performances.forEach(performance => {
          const perActCont: PerformanceActorContract = {
            performance: performance,
            actorsWithoutContract: [],
            actorWithContract: [],
            canDeletePerformance: false
          };
          this.performanceActorContract.push(perActCont);
        })
      },
      error: (error) => {
        this.errorHandle(error.status);
        console.log(error);
      }
    })
  }

  nextPage() {
    if (this.performanceActorContract.length < environment.paginationSizeForContract) {
      return;
    }
    this.indexPage++;
    this.getAllPerformance();
  }

  previousPage() {
    if (this.indexPage !== 0) {
      this.indexPage--;
      this.getAllPerformance();
    }
  }

  getContractInfo(pac: PerformanceActorContract) {
    this.actorService.getAllActorsWithoutContractByPerformanceId(pac.performance.id).subscribe({
      next: (actors ) => {
        pac.actorsWithoutContract = actors;
      },
      error: (error) => {
        this.errorHandle(error.status);
        console.log(error);
      }
    });

    this.actorService.getAllActorsByPerformanceId(pac.performance.id).subscribe({
      next: (actors ) => {
        actors.forEach(actor => {
          const existingActor = pac.actorWithContract.find(a => a.actor.id === actor.id);
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
        this.errorHandle(error.status);
        console.log(error);
      }
    });
    this.setShowDeleteButton(pac);
  }

  setShowDeleteButton(pac: PerformanceActorContract) {
    setTimeout(() => {
      pac.canDeletePerformance = pac.actorWithContract.length === 0 && pac.actorsWithoutContract.length === 0;
    }, 3000); // 3000 миллисекунд (3 секунды)
  }

  getContractByActorAndPerforId(ac: ActorWithContract, performanceId: number) {
    this.contractService.getContractByActorAndPerformanceId(ac.actor.id, performanceId).subscribe({
      next: (contract) => {
        console.log(contract);
        ac.contract = contract;
      },
      error: (error) => {
        this.errorHandle(error.status);
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
        this.errorHandle(error.status);
      }
    })
  }

  deleteActorFromPerformanceQueue(actorId: number, perforId: number, actorsWithoutContract: Actor[]) {
    this.performanceService.deleteActorFromPerformanceQueue(actorId, perforId).subscribe({
      next: () => {
        const index = actorsWithoutContract.findIndex(actor => actor.id === actorId);
        if (index !== -1) {
          actorsWithoutContract.splice(index, 1);
        }
      },
      error: (error) => {
        this.errorHandle(error.status);
      }
    })
  }

  deletePerformance(perforId: number) {
    this.performanceService.deletePerformance(perforId).subscribe({
      next: () => {
        this.router.navigate([environment.rootURL + "/performances"]);
      },
      error: (error) => {
        console.log(error);
        this.errorHandle(error.status);
      }
    })
  }

  private errorHandle(status: number) {
    if (status === 0) {
      this.messageService.showMessage("You don't have access or you have to re authenticated");
    }
    if (status === 429) {
      this.messageService.showMessage("You have reached your request limit (10 req/min)");
    }
    if (status === 400) {
      this.messageService.showMessage("Something went wrong");
    }
  }
}
