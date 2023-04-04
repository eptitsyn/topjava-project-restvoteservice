INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User2', 'user2@gmail.com', '{noop}user'),
       ('User3', 'user3@gmail.com', '{noop}user');

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

-- INSERT INTO MENU (DATE, RESTAURANT_ID, DISHES)
-- VALUES (CURRENT_DATE, 1, '[{"name":"Репа паренная","price":12}, {"name":"Борщ","price":34}]'),
--        (CURRENT_DATE, 2, '[{"name":"Минестроне","price":12}]'),
--        (CURRENT_DATE, 3, '[{"name":"Паэлья","price":12}]'),
--        (DATEADD(DAY, 1, CURRENT_DATE), 1,
--         '[{"name":"Завтрашняя Репа паренная","price":12}, {"name":"Завтрашний Борщ","price":34}]'),
--        (DATEADD(DAY, 1, CURRENT_DATE), 2, '[]'),
--        (DATEADD(DAY, 1, CURRENT_DATE), 3, '[]'),
--        (DATEADD(DAY, -1, CURRENT_DATE), 1,
--         '[{"name":"Вчерашняя Репа паренная","price":12}, {"name":"Борщ","price":34}]'),
--        (DATEADD(DAY, -1, CURRENT_DATE), 2, '[]'),
--        (DATEADD(DAY, -1, CURRENT_DATE), 3, '[]');
--
--
INSERT INTO VOTE (CASTED_DATE, CASTED_TIME, RESTAURANT_ID, USER_ID)
VALUES (DATEADD(DAY, -1, CURRENT_DATE), '9:00:00', 1, 1),
       (DATEADD(DAY, -2, CURRENT_DATE), '8:00:00', 2, 1),
       (DATEADD(DAY, -1, CURRENT_DATE), '9:00:00', 2, 2),
       (DATEADD(DAY, -2, CURRENT_DATE), '10:00:00', 2, 2),
       (DATEADD(DAY, -1, CURRENT_DATE), '10:30:00', 1, 3);
