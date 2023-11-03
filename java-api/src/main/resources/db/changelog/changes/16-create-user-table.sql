--liquibase formatted sql
--changeset IvanZhuravlev:create-user-table
CREATE TABLE IF NOT EXISTS users
(
    id bigint NOT NULL AUTO_INCREMENT,
    email character varying(256) NOT NULL,
    password character varying(256) NOT NULL,
    firstname character varying(256) NOT NULL,
    role character varying(256) NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY(id),
    CONSTRAINT user_email_unique UNIQUE (email)
    );

--rollback DROP TABLE users;