--liquibase formatted sql
--changeset IvanZhuravlev:13-create-calc-salary-function
create procedure calc_salary_for_act(in actor_id int, out result decimal(10, 2))
begin
	declare actorExperience decimal(10, 2);
    declare totalCoefficient decimal(10, 2);

select experience into actorExperience from actors where id = actor_id;

select sum(p.coefficient) into totalCoefficient
from prizes_actors pa
         join prizes p on pa.prizes_id = p.id
where pa.actors_id = actor_id;

set result = 100 * (actorExperience + ifnull(totalCoefficient, 0.0));
end;