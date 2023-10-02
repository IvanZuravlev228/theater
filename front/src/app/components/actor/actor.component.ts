import {Component, OnInit} from '@angular/core';
import {Actor} from "../../models/Actor";
import {ActorService} from "../../services/actor.service";
import {Prize} from "../../models/prize/Prize";
import {PrizeService} from "../../services/prize.service";
import {PrizesIdsRequestDto} from "../../models/prize/PrizesIdsRequestDto";

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrls: ['./actor.component.css']
})
export class ActorComponent implements OnInit{
  actors: Actor[] = [];
  prizesByActorId: Prize[] = [];

  constructor(private actorService: ActorService,
              private prizesService: PrizeService) {
  }

  ngOnInit(): void {
    this.getAllActors();
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


  showPrizesByActorId(ids: number[]) {
    let dto = new PrizesIdsRequestDto();
    dto.prizesIds = ids;
    this.prizesService.getAllPrizesByIds(dto).subscribe({
      next: (prizes) => {
        this.prizesByActorId = prizes;
        console.log(prizes);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }
}
