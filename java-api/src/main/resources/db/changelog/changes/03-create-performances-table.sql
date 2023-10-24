--liquibase formatted sql
--changeset IvanZhuravlev:03-create-performances-table
CREATE TABLE performances (
                              id bigint NOT NULL AUTO_INCREMENT,
                              name varchar(255) NOT NULL,
                              description varchar(1000) DEFAULT NULL,
                              budget decimal(10,2) NOT NULL,
                              is_deleted BOOLEAN DEFAULT 0,
                              CONSTRAINT performances_pk PRIMARY KEY(id)
);
--rollback DROP TABLE performances;