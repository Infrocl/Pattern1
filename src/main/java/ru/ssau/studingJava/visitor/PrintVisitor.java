package ru.ssau.studingJava.visitor;

import ru.ssau.studingJava.Car;
import ru.ssau.studingJava.Motorbike;

import java.util.Locale;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) {
        System.out.printf(car.getBrand());
        String[] modelNames = car.getAllModelNames();
        double[] modelPrices = car.getAllModelPrices();
        for (int i = 0; i < modelPrices.length; i++) {
            System.out.printf(Locale.UK, "\t%s; %.2f", modelNames[i], modelPrices[i]);
        }
        System.out.println();
    }

    @Override
    public void visit(Motorbike motorbike) {
        System.out.println(motorbike.getBrand());
        String[] modelNames = motorbike.getAllModelNames();
        double[] modelPrices = motorbike.getAllModelPrices();
        for (int i = 0; i < modelPrices.length; i++) {
            System.out.printf(Locale.UK, "%s; %.2f\n", modelNames[i], modelPrices[i]);
        }
    }
}
