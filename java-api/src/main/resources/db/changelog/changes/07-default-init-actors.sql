--liquibase formatted sql
--changeset IvanZhuravlev:07-default-init-actors
INSERT INTO actors(name, lastname, experience) VALUES
('John', 'Doe', 10),
('Jane', 'Smith', 8.90),
('Michael', 'Johnson', 3.70),
('Emily', 'Davis', 6.20),
('David', 'Wilson', 4.50);

