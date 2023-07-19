package ru.ssau.studingJava;

import ru.ssau.studingJava.factory.AutoFactory;
import ru.ssau.studingJava.factory.VehicleFactory;

import java.util.Arrays;

public class Vehicles {
    private static VehicleFactory factory = new AutoFactory();

    private Vehicles() {
    }

    public static void setVehicleFactory(VehicleFactory vehicleFactory) {
        factory = vehicleFactory;
    }

    public static Vehicle createInstance(String brand, int size) {
        return factory.createInstance(brand, size);
    }

    public static double modelPricesAverage(Vehicle vehicle) {
        double[] allPrices = vehicle.getAllModelPrices();
        double average = 0;
        for (double price : allPrices) {
            average += price;
        }
        return average / vehicle.getNumberOfModels();
    }

    public static void printAllModelNames(Vehicle vehicle) {
        System.out.println("Названия моделей " + vehicle.getBrand());
        System.out.println(Arrays.toString(vehicle.getAllModelNames()));
    }

    public static void printAllModelPrices(Vehicle vehicle) {
        System.out.println("Цены моделей " + vehicle.getBrand());
        System.out.println(Arrays.toString(vehicle.getAllModelPrices()));
    }

    public static void printVehicle(Vehicle vehicle) {
        System.out.println(vehicle.getBrand());
        String[] names = vehicle.getAllModelNames();
        double[] prices = vehicle.getAllModelPrices();
        for (int i = 0; i < vehicle.getNumberOfModels(); i++) {
            System.out.println(names[i] + " " + prices[i]);
        }
    }
}
