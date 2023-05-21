package com.gym.monsterfit.exceptions;

public class EmailExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailExistsException(String message) {
        super(message);
    }

}
