CREATE TABLE USERS(
    userId          VARCHAR(45) PRIMARY KEY,
    userPassword    VARCHAR(45) NOT NULL,
    userName        VARCHAR(45) NOT NULL,
);

CREATE TABLE CINEMA(
    cinemaId    NUMBER PRIMARY KEY,
    cinemaName  VARCHAR(45) NOT NULL,
    areaCode    VARCHAR(45) NOT NULL,
    theaterCode VARCHAR(45) NOT NULL,
);

CREATE TABLE HALLTYPE(
    hallTypeId      NUMBER PRIMARY KEY,
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
    reservationId   NUMBER PRIMARY KEY,
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
    noticeId   NUMBER PRIMARY KEY,
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

