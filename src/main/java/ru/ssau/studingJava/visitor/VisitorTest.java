package ru.ssau.studingJava.visitor;

import ru.ssau.studingJava.Car;
import ru.ssau.studingJava.Motorbike;

public class VisitorTest {
    public static void main(String[] args) {
        Car car = new Car("lada", 3);
        Motorbike motorbike = new Motorbike("mustang", 4);
        car.accept(new PrintVisitor());
        motorbike.accept(new PrintVisitor());
    }
}
