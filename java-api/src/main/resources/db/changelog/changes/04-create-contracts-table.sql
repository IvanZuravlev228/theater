--liquibase formatted sql
--changeset IvanZhuravlev:04-create-contracts-table
DROP TABLE IF EXISTS contracts;
CREATE TABLE contracts (
                           id bigint NOT NULL AUTO_INCREMENT,
                           actor_id bigint NOT NULL,
                           salary decimal(10,2) DEFAULT NULL,
                           CONSTRAINT contracts_pk PRIMARY KEY(id),
                           FOREIGN KEY (actor_id) REFERENCES actors (id)
);

--rollback DROP TABLE contracts;
