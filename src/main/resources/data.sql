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

INSERT INTO MENU (RESTAURANT_ID, DATE, DESCRIPTION)
VALUES (1, CURRENT_DATE, 'Manon menu'),
       (2, CURRENT_DATE, 'Pushkin menu'),
       (3, CURRENT_DATE, 'Shinok menu'),
       (1, TIMESTAMPADD(DAY, 1, CURRENT_DATE), 'Manon menu +1'),
       (2, TIMESTAMPADD(DAY, 1, CURRENT_DATE), 'Pushkin menu +1'),
       (3, TIMESTAMPADD(DAY, 1, CURRENT_DATE), 'Shinok menu +1');

