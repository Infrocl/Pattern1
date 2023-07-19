package ru.ssau.studingJava;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VehiclesTest {
    private final double ACCURACY = 0.0005;

    private Car getCar() {
        return new Car("BMW", 6);
    }

    private Motorbike getMotorbike() {
        return new Motorbike("Honda", 5);
    }

    @Test
    public void testModelPricesAverage() {
        double carAverage = Vehicles.modelPricesAverage(getCar());
        double motorbikeAverage = Vehicles.modelPricesAverage(getMotorbike());
        assertEquals(carAverage, 350000, ACCURACY);
        assertEquals(motorbikeAverage, 21000, ACCURACY);
    }

    @Test
    public void testPrintAllModelNames() {
        Vehicles.printAllModelNames(getCar());
        Vehicles.printAllModelNames(getMotorbike());
    }

    @Test
    public void testPrintAllModelPrices() {
        Vehicles.printAllModelPrices(getCar());
        Vehicles.printAllModelPrices(getMotorbike());
    }
}