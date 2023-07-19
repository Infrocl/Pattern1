package ru.ssau.studingJava.DAO;

import ru.ssau.studingJava.Vehicle;

import java.io.IOException;

public interface VehicleDAO {
    void write(Vehicle v, String fileName) throws IOException;
    Vehicle read(String fileName) throws IOException;
}
