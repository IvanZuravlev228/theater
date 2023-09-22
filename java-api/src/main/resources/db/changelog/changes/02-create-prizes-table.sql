--liquibase formatted sql
--changeset IvanZhuravlev:02-create-prizes-actors-table
DROP TABLE IF EXISTS prizes;
CREATE TABLE prizes (
                        id bigint NOT NULL AUTO_INCREMENT,
                        name varchar(255) NOT NULL,
                        coefficient decimal(4,2) DEFAULT 1.00,
                        CONSTRAINT prizes_pk PRIMARY KEY(id)
);

--rollback DROP TABLE prizes;