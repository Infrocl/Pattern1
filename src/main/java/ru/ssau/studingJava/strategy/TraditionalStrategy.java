package ru.ssau.studingJava.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TraditionalStrategy implements Strategy {

    @Override
    public Map<Integer, Integer> countEachElementNumber(int[] array) {
        Arrays.sort(array);
        Map<Integer, Integer> result = new HashMap<>();
        int count = 1;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] != array[i + 1]) {
                result.put(array[i], count);
                System.out.println(array[i] + " " + count);
                count = 1;
            } else {
                count++;
            }
        }
        if (count > 1) {
            result.put(array[array.length - 1], count);
            System.out.println(array[array.length - 1] + " " + count);
        }
        return result;
    }
}
