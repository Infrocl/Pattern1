package ru.ssau.studingJava.chain;

import ru.ssau.studingJava.Car;

public class ChainTest {
    public static void main(String[] args) {
        ChainOfResponsibility chain = new WriteInLine();
        chain.setNext(new WriteInColumn());
        Car car = new Car("lada", 4);
        chain.writeVehicle(car);
    }
}
