package ru.ssau.studingJava.factory;

import ru.ssau.studingJava.Vehicle;

public interface VehicleFactory {
    Vehicle createInstance(String brand, int size);
}
