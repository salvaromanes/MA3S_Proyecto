CREATE TABLE ITEM (ID BIGINT NOT NULL, DESCRIPTION VARCHAR, PRICE DOUBLE, TITLE VARCHAR, PRIMARY KEY (ID))
CREATE TABLE ITEM2 (NOMBRE BIGINT NOT NULL, DESCRIPTION VARCHAR, PRICE DOUBLE, TITLE VARCHAR, PRIMARY KEY (NOMBRE))
CREATE TABLE ITEM3 (NOMBRE BIGINT NOT NULL, DESCRIPTION VARCHAR, PRICE DOUBLE, TITLE VARCHAR, PRIMARY KEY (NOMBRE))
CREATE TABLE ITEM4 (ID BIGINT NOT NULL, DESCRIPTION VARCHAR, PRICE DOUBLE, TITLE VARCHAR, PRIMARY KEY (ID))
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
