package ru.ssau.studingJava.factory;

import ru.ssau.studingJava.Car;

public class AutoFactory implements VehicleFactory {
    @Override
    public Car createInstance(String brand, int size) {
        return new Car(brand, size);
    }
}
