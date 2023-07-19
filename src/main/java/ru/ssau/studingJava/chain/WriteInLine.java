package ru.ssau.studingJava.chain;

import ru.ssau.studingJava.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class WriteInLine implements ChainOfResponsibility{
    ChainOfResponsibility chain;

    @Override
    public void setNext(ChainOfResponsibility chain) {
        this.chain = chain;
    }

    @Override
    public void writeVehicle(Vehicle vehicle) {
        if (vehicle.getNumberOfModels() <= 3) {
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("files/chain.txt")))) {
                writer.printf(vehicle.getBrand());
                String[] modelNames = vehicle.getAllModelNames();
                double[] modelPrices = vehicle.getAllModelPrices();
                for (int i = 0; i < modelPrices.length; i++) {
                    writer.printf(Locale.UK, "\t%s; %.2f", modelNames[i], modelPrices[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (chain != null) {
                chain.writeVehicle(vehicle);
            }
        }
    }
}
