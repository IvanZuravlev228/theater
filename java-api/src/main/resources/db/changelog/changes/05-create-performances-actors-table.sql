--liquibase formatted sql
--changeset IvanZhuravlev:05-create-performances-actors-table
CREATE TABLE performances_actors (
                                     performances_id bigint NOT NULL,
                                     actors_id bigint NOT NULL,

                                     FOREIGN KEY (performances_id) REFERENCES performances (id),
                                     FOREIGN KEY (actors_id) REFERENCES actors (id)
);

--rollback DROP TABLE performances_actors;
