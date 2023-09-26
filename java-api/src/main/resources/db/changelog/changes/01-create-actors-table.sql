--liquibase formatted sql
--changeset IvanZhuravlev:01-create-actors-table
CREATE TABLE actors (
                        id bigint NOT NULL AUTO_INCREMENT,
                        name varchar(255) NOT NULL,
                        lastname varchar(255) NOT NULL,
                        experience decimal(4,2) DEFAULT 0.00,
                        CONSTRAINT actors_pk PRIMARY KEY(id)
);
--rollback DROP TABLE actors;