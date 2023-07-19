package ru.ssau.studingJava.command;

import ru.ssau.studingJava.Car;

import java.io.FileWriter;
import java.io.IOException;

public class CommandTest {
    public static void main(String[] args) {
        Car car = new Car("Cool car", 5);
        car.setCommand(new PrintInColumn());
        try {
            car.print(new FileWriter("files/command1.txt"));
            car.setCommand(new PrintInLine());
            car.print(new FileWriter("files/command2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
