package ru.ssau.studingJava.exception;

import java.io.Serializable;

public class DuplicateModelNameException extends Exception implements Serializable {
    private static final long serialVersionUID = 2467261613811432981L;

    public DuplicateModelNameException() {
    }

    public DuplicateModelNameException(String message) {
        super(message);
    }
}
