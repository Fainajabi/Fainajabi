package com.gofin.park.exception;

public class ParkingLotException extends Exception {

    public ParkingLotException() {
    }


    public ParkingLotException(String message) {
        super(message);
    }

    public ParkingLotException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkingLotException(Throwable cause) {
        super(cause);
    }

    public ParkingLotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
