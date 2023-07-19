package ru.ssau.studingJava;

import ru.ssau.studingJava.exception.DuplicateModelNameException;
import ru.ssau.studingJava.exception.NoSuchModelNameException;
import ru.ssau.studingJava.factory.MotorbikeFactory;

import java.util.Properties;

public class Main {
    public static void main(String[] args) throws NoSuchModelNameException, DuplicateModelNameException {
        Properties properties = Configuration.getProperties();
        System.out.printf("host: %s, username: %s, password: %s\n", properties.getProperty("host"), properties.getProperty("username"), properties.getProperty("password"));

        Vehicle car = Vehicles.createInstance("Original", 5);
        car.setModelName("Model 0", "Hello");
        System.out.println(car.getClass());
        Vehicles.setVehicleFactory(new MotorbikeFactory());
        Vehicle motorbike = Vehicles.createInstance("Original", 4);
        motorbike.setModelName("Model 0", "It's me");
        System.out.println(motorbike.getClass());

        Car clonedCar = ((Car) car).clone();
        clonedCar.setBrand("Cloned");
        clonedCar.setPriceByModelName("Model 1", 40);
        Vehicles.printAllModelPrices(clonedCar);
        Vehicles.printAllModelPrices(car);
        Vehicles.printAllModelNames(clonedCar);
        Vehicles.printAllModelNames(car);
        Motorbike clonedMotorbike = ((Motorbike) motorbike).clone();
        clonedMotorbike.setBrand("Cloned");
        clonedMotorbike.setPriceByModelName("Model 3", 5);
        Vehicles.printAllModelPrices(clonedMotorbike);
        Vehicles.printAllModelPrices(motorbike);
        Vehicles.printAllModelNames(clonedMotorbike);
        Vehicles.printAllModelNames(motorbike);
    }

}
