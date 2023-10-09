--liquibase formatted sql
--changeset IvanZhuravlev:12-default-init-prizes_actors
INSERT INTO prizes_actors(prizes_id, actors_id) VALUES
                                                    (2,1),(3,2),(4,2),(5,3),(6,3);