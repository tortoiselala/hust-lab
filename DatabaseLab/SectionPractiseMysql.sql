USE weibo;

CREATE TABLE USER(
                     UID BIGINT PRIMARY KEY AUTO_INCREMENT,
                     NAME CHAR(10) NOT NULL,
                     SEX CHAR(1) NOT NULL,
                     BYEAR INT NOT NULL,
                     CITY CHAR(10) NOT NULL,

                     CHECK(SEX = '男' OR SEX = '女')
)
    DEFAULT CHARACTER SET UTF8
    AUTO_INCREMENT = 0;


CREATE TABLE LABEL(
    LID BIGINT PRIMARY KEY AUTO_INCREMENT,
    LNAME CHAR(10) NOT NULL
)
    DEFAULT CHARACTER SET UTF8
    AUTO_INCREMENT = 0;


CREATE TABLE MBLOG(
                      BID BIGINT PRIMARY KEY AUTO_INCREMENT,
                      TITLE CHAR(10) NOT NULL,
                      UID BIGINT NOT NULL,
                      PYEAR INT NOT NULL,
                      PMONTH INT NOT NULL,
                      PDAY INT NOT NULL,
                      CONT CHAR(100) NOT NULL,

                      FOREIGN KEY (UID) REFERENCES USER(UID)
)
    DEFAULT CHARACTER SET UTF8
    AUTO_INCREMENT = 0;

CREATE TABLE B_L(
                    BID BIGINT NOT NULL,
                    UID BIGINT NOT NULL,

                    FOREIGN KEY (BID) REFERENCES MBLOG(BID),
                    FOREIGN KEY (UID) REFERENCES USER(UID)
)
    DEFAULT CHARACTER SET UTF8;

CREATE TABLE FOLLOW(
                       UID BIGINT NOT NULL,
                       UIDFLED BIGINT NOT NULL,

                       FOREIGN KEY (UID) REFERENCES USER(UID),
                       FOREIGN KEY (UIDFLED) REFERENCES USER(UID)
)
    DEFAULT CHARACTER SET UTF8;


CREATE TABLE FRIENDS(
                        UID BIGINT NOT NULL,
                        FUID BIGINT NOT NULL,

                      FOREIGN KEY (UID) REFERENCES USER(UID),
                    FOREIGN KEY (FUID) REFERENCES USER(UID)
)
    DEFAULT CHARACTER SET UTF8;

CREATE TABLE SUB(
                    UID BIGINT NOT NULL,
                    LID BIGINT NOT NULL,

                       FOREIGN KEY (UID) REFERENCES USER(UID),
                       FOREIGN KEY (LID) REFERENCES LABEL(LID)
)
    DEFAULT CHARACTER SET UTF8;

CREATE TABLE THUME(
                      UID BIGINT NOT NULL,
                      BID BIGINT NOT NULL,

                      FOREIGN KEY (UID) REFERENCES USER(UID),
                      FOREIGN KEY (BID) REFERENCES MBLOG(BID)
)
    DEFAULT CHARACTER SET UTF8;

CREATE TABLE TOPDAY(
                       TYEAR INT NOT NULL,
                       TMONTY INT NOT NULL,
                       TDAY INT NOT NULL,
                       BID BIGINT NOT NULL,
                       TNO INT NOT NULL,

                       FOREIGN KEY (BID) REFERENCES MBLOG(BID),
                       CHECK(TNO >= 1 AND TNO <= 10)
)DEFAULT CHARACTER SET UTF8;