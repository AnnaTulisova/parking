INSERT INTO USER (FIRST_NAME, LAST_NAME, PHONE, EMAIL, PASSWORD) VALUES ('Иван', 'Петров', '123', 'ivan@petrov.com', '$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C'),('Антон', 'Сидоров', '456', 'anton@777.com', '$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C'),('Инна', 'Ананьева', '789', 'inna@nanan.com', '$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C'), ('Анна', 'Тулисова', '89278376555', 'a.tulisova@mail.ru', '$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C');

insert into user_role(user_id, roles) values (1, 'USER'), (2, 'USER'), (3, 'USER'), (4, 'ADMIN');

insert into location (address) values ('Федерации, 97'), ('Северный Венец, 32'), ('Пушкинская 44');

insert into place (location_id, name) values (1,'А1'), (1,'А2'), (1, 'А3'), (1,'А4'), (1,'А5'), (1, 'А6'), (1,'А7'), (1,'А8'), (1, 'А9'), (1,'А10'), (2,'А1'), (2, 'А2'), (2, 'А3'), (2,'А4'), (2,'А5'), (2, 'А6'), (2,'А7'), (2,'А8'), (2, 'А9'), (2,'А10'), (3,'А1'), (3, 'А2'), (3, 'А3'), (3,'А4'), (2,'А5'), (3, 'А6'), (3,'А7'), (3,'А8'), (3, 'А9'), (3,'А10') ;

insert into reservation (car_number, start_date_time, end_date_time, location_id, place_id, user_id) values ('А581ТО173', '2021-05-06 19:59:00','2021-05-06 20:59:00', 1, 1, 1),('А581ТО173', '2021-05-06 21:01:00','2021-05-06 22:01:00', 1, 1, 1);