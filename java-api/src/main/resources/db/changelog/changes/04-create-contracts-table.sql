--liquibase formatted sql
--changeset IvanZhuravlev:04-create-contracts-table
CREATE TABLE contracts (
                           id bigint NOT NULL AUTO_INCREMENT,
                           actor_id bigint NOT NULL,
                           performance_id bigint NOT NULL,
                           role varchar(255) NOT NULL,
                           salary decimal(10,2) DEFAULT NULL,
                           is_deleted BOOLEAN DEFAULT 0,
                           CONSTRAINT contracts_pk PRIMARY KEY(id),
                           FOREIGN KEY (actor_id) REFERENCES actors (id),
                           FOREIGN KEY (performance_id) REFERENCES performances (id)
);
--rollback DROP TABLE contracts;
