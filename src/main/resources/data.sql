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

INSERT INTO MENU (DATE, RESTAURANT_ID, DISHES)
VALUES (CURRENT_DATE, 1, '[{"name":"Репа паренная","price":12}, {"name":"Борщ","price":34}]'),
       (CURRENT_DATE, 2, '[{"name":"Минестроне","price":12}]'),
       (CURRENT_DATE, 3, '[{"name":"Паэлья","price":12}]'),
       (DATEADD(DAY, 1, CURRENT_DATE), 1,
        '[{"name":"Завтрашняя Репа паренная","price":12}, {"name":"Борщ","price":34}]'),
       (DATEADD(DAY, 1, CURRENT_DATE), 2, '[]'),
       (DATEADD(DAY, 1, CURRENT_DATE), 3, '[]'),
       (DATEADD(DAY, -1, CURRENT_DATE), 1,
        '[{"name":"Вчерашняя Репа паренная","price":12}, {"name":"Борщ","price":34}]'),
       (DATEADD(DAY, -1, CURRENT_DATE), 2, '[]'),
       (DATEADD(DAY, -1, CURRENT_DATE), 3, '[]');
