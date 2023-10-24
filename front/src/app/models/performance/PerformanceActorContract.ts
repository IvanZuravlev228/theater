import {Performance} from "./Performance";
import {Actor} from "../actor/Actor";
import {ActorWithContract} from "../actor/ActorWithContract";

export class PerformanceActorContract {
  performance: Performance = new Performance();
  actorsWithoutContract: Actor[] = [];
  actorWithContract: ActorWithContract[] = [];
  canDeletePerformance: boolean = false;
}
