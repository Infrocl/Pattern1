package ru.ssau.studingJava.DAO;

import ru.ssau.studingJava.Car;
import ru.ssau.studingJava.Vehicles;
import ru.ssau.studingJava.exception.DuplicateModelNameException;

import java.io.IOException;


public class TestDAO {
    public static void main(String[] args) {
        SerializedCarDAO serializedCarDAO = new SerializedCarDAO();
        CharacterStreamCarDAO characterStreamCarDAO = new CharacterStreamCarDAO();
        Car car = new Car("Kia", 5);
        try {
            car.addModel("X", 50000);
            serializedCarDAO.write(car, "files/car1.bin");
            Car serializedCar = serializedCarDAO.read("files/car1.bin");
            Vehicles.printAllModelNames(serializedCar);

            characterStreamCarDAO.write(car,"files/car1.txt");
            Car readCar = characterStreamCarDAO.read("files/car1.txt");
            Vehicles.printAllModelNames(readCar);
        } catch (DuplicateModelNameException | IOException e) {
            e.printStackTrace();
        }
    }
}
