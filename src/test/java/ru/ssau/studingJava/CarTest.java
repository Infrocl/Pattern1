package ru.ssau.studingJava;

import org.testng.annotations.Test;
import ru.ssau.studingJava.exception.DuplicateModelNameException;
import ru.ssau.studingJava.exception.ModelPriceOutOfBoundsException;
import ru.ssau.studingJava.exception.NoSuchModelNameException;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CarTest {
    private Car getCar() {
        return new Car("Lada", 5);
    }

    @Test
    public void testSetBrand() {
        Car newCar = getCar();
        newCar.setBrand("Mercedes");
        assertEquals(newCar.getBrand(), "Mercedes");
    }

    @Test
    public void testGetBrand() {
        assertEquals(getCar().getBrand(), "Lada");
    }

    @Test
    public void testSetModelName() {
        Car firstCar = getCar();
        try {
            firstCar.setModelName("Model 0", "Niva");
            firstCar.setModelName("Model 2", "Vesta");
        } catch (DuplicateModelNameException | NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        assertEquals(firstCar.getAllModelNames()[0], "Niva");
        assertEquals(firstCar.getAllModelNames()[2], "Vesta");
        assertThrows(NoSuchModelNameException.class, () -> firstCar.setModelName("Machine", "Human"));
        assertThrows(DuplicateModelNameException.class, () -> firstCar.setModelName("Model 3", "Model 4"));
    }

    @Test
    public void testGetAllModelNames() {
        System.out.println("Названия моделей:");
        System.out.println(Arrays.toString(getCar().getAllModelNames()));
    }

    @Test
    public void testGetAllModelPrices() {
        System.out.println("Цены моделей:");
        System.out.println(Arrays.toString(getCar().getAllModelPrices()));
    }

    @Test
    public void testGetPriceByModelName() {
        final Car newCar = getCar();
        try {
            assertEquals(newCar.getPriceByModelName("Model 0"), 100000.0);
            assertEquals(newCar.getPriceByModelName("Model 1"), 200000.0);
            assertEquals(newCar.getPriceByModelName("Model 4"), 500000.0);
        } catch (NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        assertThrows(NoSuchModelNameException.class, () -> newCar.getPriceByModelName("Car"));
    }

    @Test
    public void testSetPriceByModelName() {
        Car newCar = getCar();
        try {
            newCar.setPriceByModelName("Model 0", 55);
            newCar.setPriceByModelName("Model 1", 100);
            newCar.setPriceByModelName("Model 4", 700);
            assertEquals(newCar.getPriceByModelName("Model 0"), 55.0);
            assertEquals(newCar.getPriceByModelName("Model 1"), 100.0);
            assertEquals(newCar.getPriceByModelName("Model 4"), 700.0);
        } catch (NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ModelPriceOutOfBoundsException.class, () -> newCar.setPriceByModelName("Model 4", -900));
        assertThrows(NoSuchModelNameException.class, () -> newCar.setPriceByModelName("Vehicle", 50));
    }

    @Test
    public void testAddModel() {
        Car newCar = getCar();
        try {
            newCar.addModel("Model 79", 790000);
            assertEquals(newCar.getPriceByModelName("Model 79"), 790000);
        } catch (DuplicateModelNameException | NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        assertEquals(newCar.getNumberOfModels(), 6);
        assertEquals(newCar.getAllModelNames()[5], "Model 79");
        assertThrows(ModelPriceOutOfBoundsException.class, () -> newCar.addModel("Model 007", -7));
        assertThrows(DuplicateModelNameException.class, () -> newCar.addModel("Model 4", 20));
    }

    @Test
    public void testRemoveModel() {
        Car firstCar = getCar();
        Car secondCar = getCar();
        try {
            firstCar.removeModel("Model 0");
            secondCar.removeModel("Model 4");
            secondCar.removeModel("Model 1");
        } catch (NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        assertEquals(firstCar.getNumberOfModels(), 4);
        System.out.println("Удаляем Модель 0:");
        System.out.println(Arrays.toString(firstCar.getAllModelNames()));
        assertEquals(secondCar.getNumberOfModels(), 3);
        System.out.println("Удаляем Модель 4:");
        System.out.println(Arrays.toString(secondCar.getAllModelNames()));
        System.out.println("Удаляем Модель 1:");
        System.out.println(Arrays.toString(secondCar.getAllModelNames()));
        System.out.println("Цены после удаления моделей 1 и 4:");
        System.out.println(Arrays.toString(secondCar.getAllModelPrices()));
        assertThrows(NoSuchModelNameException.class, () -> firstCar.removeModel("Horse"));
    }

    @Test
    public void testGetNumberOfModels() {
        assertEquals(getCar().getNumberOfModels(), 5);
    }
}