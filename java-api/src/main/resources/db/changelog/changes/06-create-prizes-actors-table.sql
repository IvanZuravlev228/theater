--liquibase formatted sql
--changeset IvanZhuravlev:06-create-prizes-actors-table
CREATE TABLE prizes_actors (
                               prizes_id bigint NOT NULL,
                               actors_id bigint NOT NULL,
                               FOREIGN KEY (prizes_id) REFERENCES prizes (id),
                               FOREIGN KEY (actors_id) REFERENCES actors (id)
);

--rollback DROP TABLE prizes_actors;
