package ru.ssau.studingJava.iterator;

import ru.ssau.studingJava.Car;

import java.util.Iterator;

public class IteratorTest {
    public static void main(String[] args) {
        Car car = new Car("Yaack", 6);
        Iterator iterator = car.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}
