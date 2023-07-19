package ru.ssau.studingJava.command;

import ru.ssau.studingJava.Vehicle;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;

public class PrintInColumn implements Command {
    @Override
    public void printCar(Vehicle car, Writer writer) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(car.getBrand());
        String[] modelNames = car.getAllModelNames();
        double[] modelPrices = car.getAllModelPrices();
        for (int i = 0; i < modelPrices.length; i++) {
            printWriter.printf(Locale.UK, "%s; %.2f\n", modelNames[i], modelPrices[i]);
        }
        printWriter.flush();
    }
}
