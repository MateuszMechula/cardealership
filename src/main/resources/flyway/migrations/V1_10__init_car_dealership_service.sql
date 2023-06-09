CREATE TABLE SERVICE
(
    SERVICE_ID   SERIAL        NOT NULL,
    SERVICE_CODE VARCHAR(32)   NOT NULL,
    DESCRIPTION  VARCHAR(64)   NOT NULL,
    PRICE        NUMERIC(7, 2) NOT NULL,
    PRIMARY KEY (SERVICE_ID),
    UNIQUE (SERVICE_CODE)
);