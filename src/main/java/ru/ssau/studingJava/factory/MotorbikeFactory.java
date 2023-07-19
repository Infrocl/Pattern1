package ru.ssau.studingJava.factory;

import ru.ssau.studingJava.Motorbike;

public class MotorbikeFactory implements VehicleFactory {
    @Override
    public Motorbike createInstance(String brand, int size) {
        return new Motorbike(brand, size);
    }
}
