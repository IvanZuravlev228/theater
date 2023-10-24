import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PerformanceService} from "../../services/performance.service";
import {ActorService} from "../../services/actor.service";
import {Performance} from "../../models/performance/Performance";
import {Actor} from "../../models/actor/Actor";
import {Prize} from "../../models/prize/Prize";
import {PrizeService} from "../../services/prize.service";
import {PrizesIdsRequestDto} from "../../models/prize/PrizesIdsRequestDto";
import {Contract} from "../../models/contract/Contract";
import {ContractService} from "../../services/contract.service";
import {environment} from "../../../environment/environment";
import {MessageService} from "../../services/message.service";

@Component({
  selector: 'app-contract-details',
  templateUrl: './contract-details.component.html',
  styleUrls: ['./contract-details.component.css']
})
export class ContractDetailsComponent implements OnInit {
  performance: Performance = new Performance();
  actor: Actor = new Actor();
  prizes: Prize[] = [];
  contractForAdd: Contract = new Contract()

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private performanceService: PerformanceService,
              private actorService: ActorService,
              private prizeService: PrizeService,
              private contractService: ContractService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.getPerformance(params['perforId']);
      this.getActor(params['actorId']);
      this.getPrizes(params['prizesIds']);
    })
  }

  getPerformance(id: number) {
    this.performanceService.getPerformanceById(id).subscribe({
      next:(performance) => {
        this.performance = performance;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  getActor(id: number) {
    this.actorService.getActorById(id).subscribe({
      next:(actor) => {
        this.actor = actor;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  getPrizes(ids: number[]) {
    let dto = new PrizesIdsRequestDto();
    dto.prizesIds = ids;
    this.prizeService.getAllPrizesByIds(dto).subscribe({
      next: (prizes) => {
        this.prizes = prizes;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  signActor() {
    this.contractForAdd.actorId = this.actor.id;
    this.contractForAdd.performanceId = this.performance.id;
    if (this.contractForAdd.role.length < 5 || this.contractForAdd.salary < 0) {
      this.messageService.showMessage("Role length should be greater than 5 and salary greater then 0");
      return;
    }

    this.contractService.saveNewContract(this.contractForAdd).subscribe({
      next: () => {
        this.router.navigate([environment.rootURL + "/contracts"]);
        this.contractForAdd.role = "";
        this.contractForAdd.salary = 0;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }
}
