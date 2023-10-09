--liquibase formatted sql
--changeset IvanZhuravlev:11-default-init-performances_actors
INSERT INTO performances_actors(performances_id, actors_id) VALUES
                                                               (1,1),(1,2),
                                                               (2,3),(2,4),
                                                               (1,3);