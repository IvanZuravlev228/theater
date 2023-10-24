--liquibase formatted sql
--changeset IvanZhuravlev:14-create-insert-procedure

create procedure set_salary_for_actor(in actor_id int)
begin
    call calc_salary_for_act(actor_id, @value);
    insert into contracts (actor_id, salary)
    values (actor_id, @value);
end;