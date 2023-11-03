import {Component, OnInit} from '@angular/core';
import {Actor} from "../../models/actor/Actor";
import {ActorService} from "../../services/actor.service";
import {Prize} from "../../models/prize/Prize";
import {PrizeService} from "../../services/prize.service";
import {PrizesIdsRequestDto} from "../../models/prize/PrizesIdsRequestDto";
import {ActorRegister} from "../../models/actor/ActorRegister";
import {MessageService} from "../../services/message.service";
import {environment} from "../../../environment/environment";

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrls: ['./actor.component.css']
})
export class ActorComponent implements OnInit{
  actors: Actor[] = [];
  prizesByActor: Prize[] = [];
  actorRegister: ActorRegister = new ActorRegister();
  prizesForRegisterActor: Prize[] = [];
  prizes: Prize[] = [];
  stopInputPrizesForRegister: boolean = false;
  indexPage: number = 0;

  constructor(private actorService: ActorService,
              private prizesService: PrizeService,
              private messageService: MessageService) {}

  ngOnInit(): void {
    this.getAllActors();
    this.getAllPrizes();
  }

  getAllActors() {
    this.actorService.getAllActors(this.indexPage).subscribe({
      next: (actors) => {
        if (actors.length !== 0) {
          this.actors = actors;
        } else {
          this.indexPage--;
        }
      },
      error: (error) => {
        console.log(error);
        this.messageService.showMessage(error.error.message);
      }
    })
  }

  showPrizesByIds(ids: number[]) {
    let dto = new PrizesIdsRequestDto();
    dto.prizesIds = ids;
    this.prizesService.getAllPrizesByIds(dto).subscribe({
      next: (prizes) => {
        this.prizesByActor = prizes;
      },
      error: (error) => {
        this.errorHandle(error);
      }
    })
  }


  addNewActor() {
    if (this.checkCorrectDataForRegisterActor(this.actorRegister)) {
      this.messageService.showMessage("Name and last name length should be greater than 4, experience should be greater or equal than 0");
      return;
    }
    this.actorService.saveNewActor(this.actorRegister).subscribe({
      next: (actor) => {
        this.actors.push(actor);
        this.actorRegister = new ActorRegister();
        this.prizesForRegisterActor = [];
        this.resetCheckboxSelection();
      },
      error: (error) => {
        this.errorHandle(error);
      }
    })
  }

  getAllPrizes() {
    this.prizesService.getAllPrizes().subscribe({
      next: (prizes) => {
        this.prizes = prizes;
      },
      error: (error) => {
        this.errorHandle(error);
      }
    })
  }

  updateSelectedPrizes(selectedPrize: Prize) {
    const selectedPrizes = this.prizes.filter(prize => prize.selected);
    console.log("selectedPrizes.length = " + selectedPrizes.length);
    if (selectedPrizes.length <= 3) {
      this.stopInputPrizesForRegister = false;
      this.actorRegister.prizeIds = selectedPrizes.map(prize => prize.id);
    } else {
      selectedPrize.selected = false;
      this.stopInputPrizesForRegister = true;
    }
  }
  previousPage() {
    if (this.indexPage !== 0) {
      this.indexPage--;
      this.getAllActors();
    }
  }

  nextPage() {
    if (this.actors.length < environment.paginationSizeForActor) {
      return;
    }
    this.indexPage++;
    this.getAllActors();
  }

  private errorHandle(error: any) {
    if (error.status === 500) {
      this.messageService.showMessage("You may have to re-authenticate");
    }
    if (error.status === 0) {
      this.messageService.showMessage("Mey be you don't gave a access to this end point");
    }
    if (error.status === 400) {
      this.messageService.showMessage("Something went wrong");
    }
  }

  private checkCorrectDataForRegisterActor(actor: ActorRegister): boolean {
    return (actor.name.length < environment.minimalLengthForActorNameAndLastname ||
      actor.lastname.length < environment.minimalLengthForActorNameAndLastname ||
      actor.experience < environment.minimalActorsExperience);
  }

  private resetCheckboxSelection() {
    this.prizes.forEach(prize => {
      prize.selected = false;
    });
  }
}
