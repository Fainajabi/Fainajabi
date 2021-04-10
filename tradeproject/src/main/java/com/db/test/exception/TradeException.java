package com.db.test.exception;

public class TradeException extends RuntimeException {

    public TradeException() {
    }

    public TradeException(String message) {
        super(message);
    }

    public TradeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TradeException(Throwable cause) {
        super(cause);
    }
}
