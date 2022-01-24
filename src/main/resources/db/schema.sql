DROP TABLE IF EXISTS client cascade;
DROP TABLE IF EXISTS credit_card cascade;
DROP TABLE IF EXISTS contact cascade;
DROP TABLE IF EXISTS "transaction" cascade;
DROP TABLE IF EXISTS currency cascade;
DROP TABLE IF EXISTS operation cascade;

--заполнить вручную пока значениями с сайта (пока хватит 3-5 валют, доллар, евро, фунт, юань)
CREATE TABLE currency (
    --по адресу JSON файл, имена полей не менял
    ID varchar(50) primary key, -- по адресу https://www.cbr-xml-daily.ru/daily_json.js поле ID
    NumCode integer,
    CharCode varchar(10),
    Nominal integer,
    Name varchar(100),
    Value double precision,
    Previous double precision
);

--виды операций
CREATE TABLE operation (
    ID serial primary key,
    category varchar(50) not null , --категория (услуги, супермаркеты, фастфуд, авто, перевод денег и т.д.)
    operation_name varchar(50) not null --название услуги (то, что в транзакции указывается - "перекресток", "ДНС", "парикмахерская" и тд)
);

--клиент
CREATE TABLE client(
    id serial primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null ,
    second_name varchar(50),
    birth_date date not null,
    username varchar(255) unique not null,
    password varchar not null,
    role varchar(20) not null
);

--create table bank_account (
--    id serial primary key, --id аккаунта, будем получать кол-во денег отсюда, к ним привязаны карты
--    client_id integer, -- к аккаунту привязан пользователь
--
--    dollar integer check (dollar >= 0 and dollar < 1000000),
--    euro integer check (euro >= 0 and euro < 1000000),
--    rub integer check (rub >= 0 and rub < 1000000000),
--    foreign key(client_id) references client(id)
--);
--не нужно. существует карта, она привязана к пользователю по определению. банковский акк лишь усложнит работу
--придется создавать в бд массив карточек.

CREATE table credit_card(
--    id serial primary key, --id карточки
    client_id integer not null , --id клиента, сюда добавить можно лишь те id, которые уже есть в табл client
    card_num varchar(19) unique primary key not null,
    currency_id varchar(50) not null , --id валюты
    money double precision not null , --денег на карте
    --check (card_num LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'
    --    or card_num LIKE '[0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9]'),
    foreign key(client_id) references client(id) ON UPDATE CASCADE,
    foreign key(currency_id) references currency(ID) ON UPDATE CASCADE
);

CREATE TABLE "transaction"(
    id serial primary key, --id транзакции
    transaction_date date not null, --дата
    sender_card varchar(19) not null, --номер карты отправителя
    recipient_card varchar(19), --номер карты получателя
    operation_id integer not null, --id операции из таблицы operation
    money_value double precision not null, --сумма платежа (перевода)
    foreign key(sender_card) references credit_card(card_num) ON UPDATE CASCADE ON DELETE CASCADE ,
    foreign key(recipient_card) references credit_card(card_num)ON UPDATE CASCADE,
    foreign key(operation_id) references operation(ID) ON UPDATE CASCADE
);


create table contact (
    id serial primary key, --id контакта
    client_id integer not null , --id клиента
    phone varchar(20), --11 цифр
    e_mail varchar(100), -- почта
    telegram varchar(100),
    meta varchar(100), --facebook
    vk varchar(100),
    CHECK (e_mail LIKE '%@%.%'),
    --CHECK (phone LIKE '/^(\+)?(\(\d{2,3}\) ?\d|\d)(([ \-]?\d)|( ?\(\d{2,3}\) ?)){5,12}\d$/'), --не работает, перенесу регулярки в код
    foreign key(client_id) references client(id) ON UPDATE CASCADE ON DELETE CASCADE
);

--SELECT c.birth_date, cc.card_num
--FROM client AS c
--Join credit_card cc on c.id = cc.client_id
--WHERE cc.card_num LIKE ('1234567890912345');

--$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y - 12345 в bcrypt
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Алексей', 'Александрович', 'Мордашов', '1965-09-26', 'AAL', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Владимир', 'Олегович', 'Потанин', '1961-01-03', 'VOP', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Владимир', 'Сергеевич', 'Лисин', '1956-05-07', 'VSL', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Вагит', 'Юсуфович', 'Алекперов', '1950-09-01', 'VUA', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Леонид', 'Викторович', 'Михельсон', '1955-08-11', 'LVM', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Генадий', 'Николаевич', 'Тимченко', '1952-11-09', 'GNT', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Алишер', 'Бурханович', 'Усманов', '1953-09-09', 'ABU', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Андрей', 'Игоревич', 'Мельниченко', '1972-03-08', 'AIM', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Павел', 'Валерьевич', 'Дуров', '1984-10-10', 'PVD', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Сулейман', 'Абусаидович', 'Керимов', '1966-03-10', 'SAK', '$2a$12$sIVBryS6ks1ti/OIcDvO5OxnjhE3kgjR/WdS3KLPRDdMs3eroa64y', 'USER');
insert into client(first_name, last_name, second_name, birth_date,username,password, role) VALUES ('Илья','Владимирович','Федоров','1998-07-26','fedorovilya','$2a$10$z4NviSQPHR65pnu6meD.VuomHoA50uwWJHrTAPdQKnI6W2Gu2YgHa','ADMIN'
);
insert into currency(ID, NumCode, CharCode, Nominal, Name, Value, Previous) VALUES ('1', '1', 'RU', 1, 'Рубль', 1, 1);
insert into currency(ID, NumCode, CharCode, Nominal, Name, Value, Previous) VALUES ('R01035', '826', 'GBP', 1, 'Фунт стерлингов Соединенного королевства', 98.1974, 97.949);
insert into currency(ID, NumCode, CharCode, Nominal, Name, Value, Previous) VALUES ('R01235', '840', 'USD', 1, 'Доллар США', 73.3583, 73.7901);
insert into currency(ID, NumCode, CharCode, Nominal, Name, Value, Previous) VALUES ('R01239', '978', 'EUR', 1, 'Евро', 83.1223, 83.1541);
insert into currency(ID, NumCode, CharCode, Nominal, Name, Value, Previous) VALUES ('R01375', '156', 'CNY', 1, 'Китайский юань', 11.5162, 11.5796);
insert into currency(ID, NumCode, CharCode, Nominal, Name, Value, Previous) VALUES ('R01820', '392', 'JPY', 100, 'Японских иен', 64.1833, 64.5554);

insert into operation(category, operation_name) VALUES ('фастфуд', 'KFC');
insert into operation(category, operation_name) VALUES ('фастфуд', 'MC');
insert into operation(category, operation_name) VALUES ('фастфуд', 'B.K.');
insert into operation(category, operation_name) VALUES ('Продуктовый магазин', 'Пятерочка');
insert into operation(category, operation_name) VALUES ('Продуктовый магазин', 'Перекресток');
insert into operation(category, operation_name) VALUES ('Продуктовый магазин', 'Дикси');
insert into operation(category, operation_name) VALUES ('Электротехника', 'М.Видео');
insert into operation(category, operation_name) VALUES ('Электронная площадка', 'Алиэкспресс');
insert into operation(category, operation_name) VALUES ('Электронная площадка', 'Wildberries');

insert into credit_card(client_id, card_num, currency_id, money) VALUES (1, '1234-5678-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (2, '1324-5678-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (3, '1234-5878-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (4, '1234-5678-9102-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (5, '1234-5678-9012-3546', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (6, '1243-5678-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (7, '1234-5687-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (8, '1234-5678-9012-3465', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (9, '4231-5678-9012-3456', 1, 19650926000);

--новое позже проверить
insert into credit_card(client_id, card_num, currency_id, money) VALUES (1, '1231-1111-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (1, '1232-1111-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (1, '1233-1111-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (2, '1320-2222-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (2, '1322-2222-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (2, '1323-2222-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (2, '1324-2222-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (2, '1325-2222-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (3, '1230-3333-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (3, '1231-3333-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (3, '1232-3333-9012-3456', 1, 19650926000);
insert into credit_card(client_id, card_num, currency_id, money) VALUES (3, '1233-3333-9012-3456', 1, 19650926000);


insert into contact(client_id, phone, e_mail) VALUES (1, '+79032145678', 'Mordashov@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (2, '+79021345678', 'Potanin@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (3, '+79012435678', 'Lisin@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (4, '+79012345768', 'Alekperov@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (5, '+79012354678', 'Muxelson@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (6, '+79013245678', 'Timchenko@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (7, '+79012346578', 'Ysmanov@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (8, '+79012345578', 'Mel@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (9, '+79012345687', 'Dyrov@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (10, '+79012345678', 'Karimov@mail.ru');
insert into contact(client_id, phone, e_mail) VALUES (11, '+79153870385', 'fyodorov1998@gmail.com');


insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('1995-09-09', '4231-5678-9012-3456', '1234-5678-9012-3465', 1, 19650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2017-03-08', '1234-5878-9012-3456', '1234-5678-9102-3456', 2, 29650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2016-05-07', '1234-5678-9012-3465', '4231-5678-9012-3456', 3, 39650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2019-09-01', '1234-5678-9012-3465', '1234-5678-9012-3546', 4, 49650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2019-11-09', '1234-5678-9102-3456', '1234-5678-9012-3465', 5, 59650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2016-03-10', '1234-5878-9012-3456', '4231-5678-9012-3456', 6, 69650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2016-04-11', '4231-5678-9012-3456', '1234-5678-9012-3465', 7, 79650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2019-08-19', '4231-5678-9012-3456', '1234-5878-9012-3456', 8, 89650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2021-01-09', '1234-5678-9012-3456', '1234-5678-9012-3546', 9, 99650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2021-04-29', '1234-5687-9012-3456', '1234-5878-9012-3456', 9, 9650926);

--новое, позже проверить
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2017-03-08', '1234-5878-9012-3456', '1234-5678-9102-3456', 9, 29650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2016-05-07', '1234-5678-9012-3465', '4231-5678-9012-3456', 8, 39650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2019-09-01', '1234-5678-9012-3465', '1234-5678-9012-3546', 7, 49650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2019-11-09', '1234-5678-9102-3456', '1234-5678-9012-3465', 6, 59650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2016-03-10', '1234-5878-9012-3456', '4231-5678-9012-3456', 5, 69650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2016-04-11', '4231-5678-9012-3456', '1234-5678-9012-3465', 4, 79650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2019-08-19', '4231-5678-9012-3456', '1234-5878-9012-3456', 3, 89650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2021-01-09', '1234-5678-9012-3456', '1234-5678-9012-3546', 2, 99650926);
insert into transaction(transaction_date, sender_card, recipient_card, operation_id, money_value) VALUES ('2021-04-29', '1234-5687-9012-3456', '1234-5878-9012-3456', 1, 9650926);