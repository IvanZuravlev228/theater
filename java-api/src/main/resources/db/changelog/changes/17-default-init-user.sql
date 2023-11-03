--liquibase formatted sql
--changeset IvanZhuravlev:17-default-init-user
INSERT INTO users(email, firstname, password, role) VALUES
                ('ivan_director@gmail.com', 'Ivan', '$2a$10$R/dyfNKaRwbOJVnOf8j51.tCBuOjnfTGo21kFIg9Z8PVetIXuWxBW', 'DIRECTOR');