--liquibase formatted sql
--changeset IvanZhuravlev:15-create-insert-salary-trigger

create trigger insert_salary_tr
    after insert on actors
    for each row
begin
    call set_salary_for_actor(new.id);
    end;
