package ru.ssau.studingJava.exception;

import java.io.Serializable;

public class ModelPriceOutOfBoundsException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 6443106169012689608L;

    public ModelPriceOutOfBoundsException() {
    }

    public ModelPriceOutOfBoundsException(String message) {
        super(message);
    }
}
