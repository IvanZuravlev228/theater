--liquibase formatted sql
--changeset IvanZhuravlev:05-create-performances-actors-table
DROP TABLE IF EXISTS performances_actors;
CREATE TABLE performances_actors (
                                     performances_id bigint NOT NULL,
                                     actor_id bigint NOT NULL,

                                     FOREIGN KEY (performances_id) REFERENCES performances (id),
                                     FOREIGN KEY (actor_id) REFERENCES actors (id)
);

--rollback DROP TABLE performances_actors;
