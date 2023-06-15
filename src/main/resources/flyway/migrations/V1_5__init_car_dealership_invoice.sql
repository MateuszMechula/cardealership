CREATE TABLE INVOICE
(
    INVOICE_ID     SERIAL                   NOT NULL,
    INVOICE_NUMBER VARCHAR(64)              NOT NULL,
    DATE_TIME      TIMESTAMP WITH TIME ZONE NOT NULL,
    CAR_TO_BUY_ID  INT                      NOT NULL,
    CUSTOMER_ID    INT                      NOT NULL,
    SALESMAN_ID    INT                      NOT NULL,
    PRIMARY KEY (INVOICE_ID),
    UNIQUE (INVOICE_NUMBER),
    CONSTRAINT FK_INVOICE_CAR_TO_BUY
        FOREIGN KEY (CAR_TO_BUY_ID)
            REFERENCES CAR_TO_BUY (CAR_TO_BUY_ID),
    CONSTRAINT FK_INVOICE_CUSTOMER
        FOREIGN KEY (CUSTOMER_ID)
            REFERENCES CUSTOMER (CUSTOMER_ID),
    CONSTRAINT FK_INVOICE_SALESMAN
        FOREIGN KEY (SALESMAN_ID)
            REFERENCES SALESMAN (SALESMAN_ID)
);