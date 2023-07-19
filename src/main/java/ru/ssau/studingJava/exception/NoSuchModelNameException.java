package ru.ssau.studingJava.exception;

import java.io.Serializable;

public class NoSuchModelNameException extends Exception implements Serializable {
    private final static long serialVersionUID = 2856904914247051073L;

    public NoSuchModelNameException() {
    }

    public NoSuchModelNameException(String message) {
        super(message);
    }
}
