--liquibase formatted sql
--changeset IvanZhuravlev:03-create-performances-table
DROP TABLE IF EXISTS performances;
CREATE TABLE performances (
                              id bigint NOT NULL AUTO_INCREMENT,
                              name varchar(255) NOT NULL,
                              description varchar(255) DEFAULT NULL,
                              budget decimal(10,2) NOT NULL,
                              CONSTRAINT performances_pk PRIMARY KEY(id)
);

--rollback DROP TABLE performances;