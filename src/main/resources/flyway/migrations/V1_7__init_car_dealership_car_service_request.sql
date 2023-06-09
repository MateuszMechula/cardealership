CREATE TABLE CAR_SERVICE_REQUEST
(
    CAR_SERVICE_REQUEST_ID     SERIAL                   NOT NULL,
    CAR_SERVICE_REQUEST_NUMBER VARCHAR(32)              NOT NULL,
    RECEIVED_DATE_TIME         TIMESTAMP WITH TIME ZONE NOT NULL,
    COMPLETED_DATE_TIME        TIMESTAMP WITH TIME ZONE,
    CUSTOMER_COMMENT           TEXT,
    CUSTOMER_ID                INT                      NOT NULL,
    CAR_TO_SERVICE_ID          INT                      NOT NULL,
    PRIMARY KEY (CAR_SERVICE_REQUEST_ID),
    UNIQUE (CAR_SERVICE_REQUEST_NUMBER),
    CONSTRAINT FK_CAR_SERVICE_REQUEST_CUSTOMER
        FOREIGN KEY (CUSTOMER_ID)
            REFERENCES CUSTOMER (CUSTOMER_ID),
    CONSTRAINT FK_CAR_SERVICE_REQUEST_CAR_TO_SERVICE
        FOREIGN KEY (CAR_TO_SERVICE_ID)
            REFERENCES CAR_TO_SERVICE (CAR_TO_SERVICE_ID)
);