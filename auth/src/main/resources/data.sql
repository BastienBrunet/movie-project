INSERT INTO roles(id, name) VALUES
                                     ('27d66833-8bc0-4873-9eaa-b3c1e8d302b0', 'ROLE_ADMIN'),
                                     ('a8e34066-9eec-49c2-9335-be85f6d20279', 'ROLE_USER')
    ON Conflict(id) DO NOTHING;

INSERT INTO user_status(id, name) VALUES
                                     ('7dac7d38-f35b-4e37-8629-84967a240fdc', 'Open'),
                                     ('313f6e74-cab8-4ec2-9433-784e4e8bee47', 'Closed')
    ON Conflict(id) DO NOTHING;

INSERT INTO users(id, created_on, last_updated_on, password, username, status_id) VALUES
                                      ('dd5f8ced-6f8e-44b9-8d29-7b9d7a51583f', '2024-05-14 17:01:01.248605 +00:00', '2024-05-15 12:59:02.762696 +00:00', '$2a$10$Tfs61tFe06IFDp7ZM4o//Omff5zOCCl.d1cKSn3BdfnW2Ibw9aUiW', 'admin@test.com', '7dac7d38-f35b-4e37-8629-84967a240fdc')
    ON Conflict(id) DO NOTHING;

INSERT INTO user_role(user_id, role_id) VALUES
                                      ('dd5f8ced-6f8e-44b9-8d29-7b9d7a51583f', '27d66833-8bc0-4873-9eaa-b3c1e8d302b0')
    ON Conflict(user_id, role_id) DO NOTHING;