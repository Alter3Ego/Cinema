DROP database IF EXISTS cinema;
CREATE SCHEMA `cinema` DEFAULT CHARACTER SET utf8mb4;
USE cinema;

CREATE TABLE roles (
	roleId INT PRIMARY KEY NOT NULL UNIQUE,
	role  VARCHAR(20) NOT NULL
);

CREATE TABLE users (
	userId INT PRIMARY KEY auto_increment NOT NULL UNIQUE,
	firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    balance DECIMAL UNSIGNED  default  0,
    roleId INT default 0,
    FOREIGN KEY (roleId) REFERENCES roles(roleId)
);

CREATE TABLE films (
	filmId INT PRIMARY KEY auto_increment NOT NULL UNIQUE,
	title  VARCHAR(255) NOT NULL,
    releaseYear int NOT NULL,
    price DECIMAL NOT NULL,
    genre  VARCHAR(50) NOT NULL,
    producer VARCHAR(80) NOT NULL
);

CREATE TABLE sessions  (
	sessionId INT PRIMARY KEY auto_increment NOT NULL UNIQUE,
    dateTime datetime NOT NULL,
	 filmId INT,
     numberOfTickets  INT default 0,
	CONSTRAINT FK_1 FOREIGN KEY(filmId) REFERENCES films(filmId) on delete cascade
);

CREATE TABLE tickets (
ticketId INT PRIMARY KEY auto_increment NOT NULL UNIQUE,
userId INT,
FOREIGN KEY (userId) REFERENCES users(userId),
sessionId INT,
FOREIGN KEY (sessionId) REFERENCES sessions(sessionId),
place INT,
unique(place, sessionId)
);


INSERT INTO cinema.roles (roleId, role)
VALUES (0, 'user');

INSERT INTO cinema.roles (roleId, role)
VALUES (1, 'admin');





