INSERT INTO cinema.users (firstName, lastName, email, password, balance, roleId)
VALUES ('Oleksandr', 'Omelchenko', 'alexmir98@ukr.net', '712A5CCEF107EEB52FA91B63C4ABF7B7', DEFAULT, 1); #444qwertY

INSERT INTO cinema.users (firstName, lastName, email, password, balance, roleId)
VALUES ('Admin', 'Test', 'admin@gmail.com', '9AF007B6287C4F6836BB4078EB4BF111', DEFAULT, 1); #123qwertY

INSERT INTO cinema.users (firstName, lastName, email, password, balance, roleId)
VALUES ('Alex', 'Meison', 'meison@gmail.com', '7AAEBC83A5E325EAE089C9E4F43772B0', DEFAULT, DEFAULT); #1234qwertY

INSERT INTO cinema.users (firstName, lastName, email, password, balance, roleId)
VALUES ('Іван', 'Іванов', 'ivan@gmail.com', '50472C32445A382B7B509E185C2B2ADE', DEFAULT, DEFAULT); #Ivanov12

INSERT INTO cinema.users (firstName, lastName, email, password, balance, roleId)
VALUES ('User', 'User', 'user@gmail.com', '6EDF26F6E0BADFF12FCA32B16DB38BF2', DEFAULT, DEFAULT); #User1234

INSERT INTO cinema.users (firstName, lastName, email, password, balance, roleId)
VALUES ('Петро', 'Черешня', 'cheresh@ukr.net', '712A5CCEF107EEB52FA91B63C4ABF7B7', DEFAULT, DEFAULT); #444qwertY

INSERT INTO cinema.users (firstName, lastName, email, password, balance, roleId)
VALUES ('Мікеланджело', 'Буонарроті', 'Miko98@ukr.net', '712A5CCEF107EEB52FA91B63C4ABF7B7', DEFAULT, DEFAULT); #444qwertY

INSERT INTO cinema.films  (title,  releaseYear, price, genre, producer)
VALUES ('Das Boot', '1981', '300', 'Drama', 'Wolfgang Petersen');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('Braveheart','1995', '250', 'Drama', 'Mel Gibson');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('Coma','2019', '350', 'Action', 'Nikita Argunov');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('Mad Max: Fury Road','2015', '400', 'Action', 'George Miller');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('Deadpool','2016', '300', 'Action', 'Tim Miller');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('Avengers: Age of Ultron','2015', '350', 'Action', 'Joss Whedon');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('Jaws', 1975, '200', 'Thriller', 'Steven Spielberg');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('It', 2017, '300', 'Horror', 'Andy Muschietti');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('The Pyramid', 2014, '250', 'Thriller', 'Gregory Levasseur');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('The Witch', 2015, '350', 'Horror', 'Robert Eggers');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('Men in Black', 1997, '200', 'Comedy', 'Barry Sonnenfeld');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('Pixels', 2015, '300', 'Comedy', 'Chris Columbus');

INSERT INTO cinema.films (title,  releaseYear, price, genre, producer)
VALUES ('The Wolf of Wall Street', 2013, '350', 'Comedy', 'Martin Scorsese');

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-14 09:00:00', 1);

INSERT INTO cinema.sessions (dateTime, filmId, numberOfTickets)
VALUES ('2022-06-14 11:00:00', 2, 3);

INSERT INTO cinema.sessions (dateTime, filmId, numberOfTickets)
VALUES ('2022-06-14 14:00:00', 3, 5);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-14 17:00:00', 4);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-14 20:00:00', 5);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-15 08:00:00', 1);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-15 12:00:00', 3);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-15 15:00:00', 4);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-15 16:00:00', 9);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-15 21:00:00', 4);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-16 08:00:00', 8);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-16 12:00:00', 3);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-16 15:00:00', 7);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-06-17 16:00:00', 9);

INSERT INTO cinema.sessions (dateTime, filmId)
VALUES ('2022-07-18 21:00:00', 10);

INSERT INTO cinema.tickets (userId, sessionId, place)
VALUES (1, 3, 1);

INSERT INTO cinema.tickets (userId, sessionId, place)
VALUES (2, 3, 3);

INSERT INTO cinema.tickets (userId, sessionId, place)
VALUES (3, 3, 4);

INSERT INTO cinema.tickets (userId, sessionId, place)
VALUES (4, 3, 7);

INSERT INTO cinema.tickets (userId, sessionId, place)
VALUES (5, 3, 8);

INSERT INTO cinema.tickets (userId, sessionId, place)
VALUES (1, 2, 3);

INSERT INTO cinema.tickets (userId, sessionId, place)
VALUES (1, 2, 5);

INSERT INTO cinema.tickets (userId, sessionId, place)
VALUES (2, 2, 8);





