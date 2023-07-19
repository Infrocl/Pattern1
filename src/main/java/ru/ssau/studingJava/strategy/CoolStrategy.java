package ru.ssau.studingJava.strategy;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoolStrategy implements Strategy {

    @Override
    public Map<Integer, Long> countEachElementNumber(int[] array) {
        Map<Integer, Long> result = IntStream.of(array).boxed().toList().stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        System.out.println(result);
        return result;
    }
}
