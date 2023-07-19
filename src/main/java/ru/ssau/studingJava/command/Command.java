package ru.ssau.studingJava.command;

import ru.ssau.studingJava.Vehicle;

import java.io.Writer;

public interface Command {
    void printCar(Vehicle car, Writer writer);
}
