package ru.ssau.studingJava.DAO;

import ru.ssau.studingJava.Car;
import ru.ssau.studingJava.Vehicle;
import ru.ssau.studingJava.exception.DuplicateModelNameException;

import java.io.*;
import java.util.Locale;

public class CharacterStreamCarDAO implements VehicleDAO {
    @Override
    public void write(Vehicle v, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        writer.println(v.getBrand());
        writer.println(v.getNumberOfModels());
        String[] modelNames = v.getAllModelNames();
        double[] modelPrices = v.getAllModelPrices();
        for (int i = 0; i < modelPrices.length; i++) {
            writer.printf(Locale.UK, "%s; %.2f\n", modelNames[i], modelPrices[i]);
        }
        writer.close();
    }

    @Override
    public Car read(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Car car = null;
        try {
            String brand = reader.readLine();
            car = new Car(brand, 0);
            int size = Integer.parseInt(reader.readLine());
            for (int i = 0; i < size; i++) {
                String[] line = reader.readLine().split("; ");
                car.addModel(line[0], Double.parseDouble(line[1]));
            }
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
        return car;
    }
}
