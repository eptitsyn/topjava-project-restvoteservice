INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (ID, NAME)
VALUES (1, 'Manon'),
       (2, 'Pushkin'),
       (3, 'Shinok');

ALTER TABLE RESTAURANT
    ALTER COLUMN id RESTART WITH 4;

INSERT INTO MENU (ID, DATE, RESTAURANT_ID, MENU)
VALUES (1, CURRENT_DATE, 1, '{}'),
       (2, CURRENT_DATE, 2, '{}'),
       (3, CURRENT_DATE, 3, '{}'),
       (4, DATEADD(DAY, 1, CURRENT_DATE), 1, '{}'),
       (5, DATEADD(DAY, 1, CURRENT_DATE), 2, '{}'),
       (6, DATEADD(DAY, 1, CURRENT_DATE), 3, '{}'),
       (7, DATEADD(DAY, -1, CURRENT_DATE), 1, '{}'),
       (8, DATEADD(DAY, -1, CURRENT_DATE), 2, '{}'),
       (9, DATEADD(DAY, -1, CURRENT_DATE), 3, '{}');

ALTER TABLE MENU
    ALTER COLUMN id RESTART WITH 10;

