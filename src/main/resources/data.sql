INSERT INTO USER (FIRST_NAME, LAST_NAME, PHONE, EMAIL, PASSWORD) VALUES ('Иван', 'Петров', '123', 'ivan@petrov.com', '$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C'),('Антон', 'Сидоров', '456', 'anton@777.com', '$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C'),('Инна', 'Ананьева', '789', 'inna@nanan.com', '$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C'), ('Анна', 'Тулисова', '89278376555', 'a.tulisova@mail.ru', '$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C');

insert into user_role(user_id, roles) values (1, 'USER'), (2, 'USER'), (3, 'USER');

insert into location (address) values ('Федерации, 97'), ('Северный Венец, 32'), ('Пушкинская 44');

insert into place (location_id, name) values (1,'А1'), (1,'А2'), (1, 'А3');