DELETE FROM user_roles;
DELETE FROM user_access_rights;
DELETE FROM measurements;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO user_access_rights (user_id, follower_id)
VALUES (100001, 100000);

INSERT INTO measurements (date, weight, waist, hips, user_id)
VALUES ('2021-03-09', 48.5, 58, 86, 100000),
       ('2021-03-01', 48.1, 58, 86, 100000),
       ('2021-02-22', 48.0, 58, 86, 100000),
       ('2021-02-22', 48.0, 58, 86, 100001);
