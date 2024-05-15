INSERT INTO roles(id, name) VALUES
                                     ('27d66833-8bc0-4873-9eaa-b3c1e8d302b0', 'ROLE_ADMIN'),
                                     ('a8e34066-9eec-49c2-9335-be85f6d20279', 'ROLE_USER')
    ON Conflict(id) DO NOTHING;

INSERT INTO user_status(id, name) VALUES
                                     ('7dac7d38-f35b-4e37-8629-84967a240fdc', 'Open'),
                                     ('313f6e74-cab8-4ec2-9433-784e4e8bee47', 'Closed')
    ON Conflict(id) DO NOTHING;

/*INSERT INTO users(id, username, password, created_on, last_updated_on) VALUES
                                      ('7dac7d38-f35b-4e37-8629-84967a240fdc', 'Open'),
                                      ('313f6e74-cab8-4ec2-9433-784e4e8bee47', 'Closed')
    ON Conflict(id) DO NOTHING;*/