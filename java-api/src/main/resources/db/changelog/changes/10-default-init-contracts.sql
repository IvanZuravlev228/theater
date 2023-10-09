--liquibase formatted sql
--changeset IvanZhuravlev:10-default-init-contracts
INSERT INTO contracts(actor_id, performance_id, role, salary) VALUES
                                            (1, 1, "role 1", 690.00),
                                            (2, 1, "role 2", 940.00),
                                            (3, 2, "role 3", 390.00),
                                            (4, 2, "role 4", 620.00);
