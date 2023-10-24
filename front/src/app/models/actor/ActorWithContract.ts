import {Contract} from "../contract/Contract";
import {Actor} from "./Actor";

export class ActorWithContract {
  actor: Actor = new Actor();
  contract: Contract = new Contract();
}
