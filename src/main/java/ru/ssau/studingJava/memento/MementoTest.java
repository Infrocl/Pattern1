package ru.ssau.studingJava.memento;

import ru.ssau.studingJava.Car;
import ru.ssau.studingJava.Vehicles;
import ru.ssau.studingJava.exception.DuplicateModelNameException;
import ru.ssau.studingJava.exception.NoSuchModelNameException;

public class MementoTest {
    public static void main(String[] args) {
        Car car = new Car("Ghbdtn", 4);
        car.createMemento();
        Vehicles.printVehicle(car);
        try {
            car.setModelName("Model 0", "Asd");
            car.setPriceByModelName("Asd", 12);
        } catch (DuplicateModelNameException | NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        Vehicles.printVehicle(car);
        car.setMemento();
        Vehicles.printVehicle(car);
    }
}
