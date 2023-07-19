package ru.ssau.studingJava.strategy;

import java.io.*;

public class StrategyTest {
    public static void main(String[] args) {
//        int[] array = {1, 3, 8, 1, 6, 3, 3, 3, 8};
//        try {
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("files/strategy.bin"));
//            out.writeObject(array);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        int[] array = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]))) {
            array = (int[]) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        CoolStrategy coolStrategy = new CoolStrategy();
        TraditionalStrategy traditionalStrategy = new TraditionalStrategy();
        coolStrategy.countEachElementNumber(array);
        traditionalStrategy.countEachElementNumber(array);
    }
}
