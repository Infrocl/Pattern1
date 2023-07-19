package ru.ssau.studingJava.command;

import ru.ssau.studingJava.Vehicle;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;

public class PrintInLine implements Command {
    @Override
    public void printCar(Vehicle car, Writer writer) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.printf(car.getBrand());
        String[] modelNames = car.getAllModelNames();
        double[] modelPrices = car.getAllModelPrices();
        for (int i = 0; i < modelPrices.length; i++) {
            printWriter.printf(Locale.UK, "\t%s; %.2f", modelNames[i], modelPrices[i]);
        }
        printWriter.flush();
    }
}
