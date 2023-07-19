package ru.ssau.studingJava.DAO;

import ru.ssau.studingJava.Car;
import ru.ssau.studingJava.Vehicle;

import java.io.*;

public class SerializedCarDAO implements VehicleDAO {
    @Override
    public void write(Vehicle v, String fileName) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        output.writeObject(v);
        output.close();
    }

    @Override
    public Car read(String fileName) throws IOException {
        Car car = null;
        try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            car = (Car) input.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return car;
    }
}
