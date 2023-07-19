package ru.ssau.studingJava.chain;

import ru.ssau.studingJava.Vehicle;

public interface ChainOfResponsibility {
    void setNext(ChainOfResponsibility chain);

    void writeVehicle(Vehicle vehicle);
}
