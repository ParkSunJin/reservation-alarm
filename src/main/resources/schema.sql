CREATE TABLE USERS(
    userId          VARCHAR(45) PRIMARY KEY,
    userPassword    VARCHAR(45) NOT NULL,
    userName        VARCHAR(45) NOT NULL,
);

CREATE TABLE Theater(
    theaterId    NUMBER AUTO_INCREMENT PRIMARY KEY,
    theaterName  VARCHAR(45) NOT NULL,
    theaterCode VARCHAR(45) NOT NULL,
    areaName VARCHAR(45) NOT NULL,
    areaCode    VARCHAR(45) NOT NULL,
    theaterNameEng    VARCHAR(45),
    areaNameEng    VARCHAR(45)
);

CREATE TABLE HALLTYPE(
    hallTypeId      NUMBER AUTO_INCREMENT PRIMARY KEY,
    cinemaId        NUMBER NOT NULL,
    hallTypeName    VARCHAR(45) NOT NULL,
    FOREIGN KEY(cinemaId) REFERENCES CINEMA (cinemaId)
);

CREATE TABLE MOVIE(
    movieId     NUMBER AUTO_INCREMENT PRIMARY KEY,
    movieTitle   VARCHAR(45) NOT NULL UNIQUE,
    movieImageSrc VARCHAR(200) NOT NULL,
    movieScore  NUMBER NOT NULL,
    openingDate DATE NOT NULL,
    reservationLink VARCHAR(200) NOT NULL,
    isOpened    TINYINT(1)
);

CREATE TABLE RESERVATION(
    reservationId   NUMBER AUTO_INCREMENT PRIMARY KEY,
    userId          VARCHAR(45) NOT NULL,
    movieId         NUMBER NOT NULL,
    hallTypeId          NUMBER NOT NULL,
    desiredDate     DATE NOT NULL,
    cinemaId        NUMBER NOT NULL,
    FOREIGN KEY(userId) REFERENCES USERS (userId),
    FOREIGN KEY(movieId) REFERENCES MOVIE (movieId),
    FOREIGN KEY(hallTypeId) REFERENCES HALLTYPE (hallTypeId),
    FOREIGN KEY(cinemaId) REFERENCES CINEMA (cinemaId),
);

CREATE TABLE NOTICE(
    noticeId   NUMBER AUTO_INCREMENT PRIMARY KEY,
    userId          VARCHAR(45) NOT NULL,
    movieId         NUMBER NOT NULL,
    hallTypeId          NUMBER NOT NULL,
    desiredDate     DATE NOT NULL,
    cinemaId        NUMBER NOT NULL,
    FOREIGN KEY(userId) REFERENCES USERS (userId),
    FOREIGN KEY(movieId) REFERENCES MOVIE (movieId),
    FOREIGN KEY(hallTypeId) REFERENCES HALLTYPE (hallTypeId),
    FOREIGN KEY(cinemaId) REFERENCES CINEMA (cinemaId),
);

ALTER TABLE RESERVATION ADD FOREIGN KEY(movieId) REFERENCES MOVIE(movieId);

