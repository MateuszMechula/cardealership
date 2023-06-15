package pl.cardealership.business.management;

public interface Keys {

    enum InputDataGroup {
        BUY_FIRST_TIME,
        BUY_AGAIN,
        SERVICE_REQUEST,
        DO_THE_SERVICE
    }

    enum Entity {
        SALESMAN,
        MECHANIC,
        CAR,
        SERVICE,
        PART,
        CUSTOMER
    }

    enum Constants {
        WHAT,
        FINISH
    }
}
