import {Component, OnInit} from '@angular/core';
import {Actor} from "../../models/actor/Actor";
import {ActorService} from "../../services/actor.service";
import {Prize} from "../../models/prize/Prize";
import {PrizeService} from "../../services/prize.service";
import {PrizesIdsRequestDto} from "../../models/prize/PrizesIdsRequestDto";
import {ActorRegister} from "../../models/actor/ActorRegister";
import {PrizeRequest} from "../../models/prize/PrizeRequest";
import {MessageService} from "../../services/message.service";

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

  constructor(private actorService: ActorService,
              private prizesService: PrizeService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.getAllActors();
    this.getAllPrizes();
  }

  getAllActors() {
    this.actorService.getAllActors().subscribe({
      next: (actors) => {
        this.actors = actors;
        console.log(this.actors[0].prizesIds);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }


  showPrizesByIds(ids: number[]) {
    let dto = new PrizesIdsRequestDto();
    dto.prizesIds = ids;
    this.prizesService.getAllPrizesByIds(dto).subscribe({
      next: (prizes) => {
        this.prizesByActor = prizes;
        // console.log(prizes);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  private checkCorrectDataForRegisterActor(actor: ActorRegister): boolean {
    return (actor.name.length < 4 || actor.lastname.length < 4 || actor.experience < 0);
  }

  private resetCheckboxSelection() {
    this.prizes.forEach(prize => {
      prize.selected = false;
    });
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
        console.log(error);
      }
    })
  }

  getAllPrizes() {
    this.prizesService.getAllPrizes().subscribe({
      next: (prizes) => {
        this.prizes = prizes;
        // console.log(prizes);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  stopInputPrizesForRegister: boolean = false;
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

  deleteActor(actorId: number) {
    this.actorService.deleteActorById(actorId).subscribe({
      next: () => {
        const index = this.actors.findIndex(actor => actor.id === actorId);
        if (index !== -1) {
          this.actors.splice(index, 1);
        }
      },
      error: (error) => {
        console.log(error);
        this.messageService.showMessage("Something went wrong. May be this actor is playing in some performance?")
      }
    })
  }
}
