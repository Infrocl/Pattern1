package ru.ssau.studingJava.visitor;

import ru.ssau.studingJava.Car;
import ru.ssau.studingJava.Motorbike;

public interface Visitor {
    void visit(Car car);

    void visit(Motorbike motorbike);
}
