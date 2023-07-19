package ru.ssau.studingJava;

import org.testng.annotations.Test;
import ru.ssau.studingJava.exception.DuplicateModelNameException;
import ru.ssau.studingJava.exception.ModelPriceOutOfBoundsException;
import ru.ssau.studingJava.exception.NoSuchModelNameException;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class MotorbikeTest {
    private Motorbike getMotorbike() {
        return new Motorbike("Toyota", 7);
    }

    @Test
    public void testGetBrand() {
        assertEquals(getMotorbike().getBrand(), "Toyota");
    }

    @Test
    public void testSetBrand() {
        Motorbike newMotorbike = getMotorbike();
        newMotorbike.setBrand("Kia");
        assertEquals(newMotorbike.getBrand(), "Kia");
    }

    @Test
    public void testGetNumberOfModels() {
        assertEquals(getMotorbike().getNumberOfModels(), 7);
    }

    @Test
    public void testSetModelName() {
        Motorbike newMotorbike = getMotorbike();
        try {
            newMotorbike.setModelName("Model 0", "U");
            newMotorbike.setModelName("Model 4", "I");
            assertEquals(newMotorbike.getAllModelNames()[0], "U");
            assertEquals(newMotorbike.getAllModelNames()[4], "I");
        } catch (NoSuchModelNameException | DuplicateModelNameException e) {
            throw new RuntimeException(e);
        }
        assertThrows(NoSuchModelNameException.class, () -> newMotorbike.setModelName("Nokia", "Windows Phone"));
        assertThrows(DuplicateModelNameException.class, () -> newMotorbike.setModelName("Model 2", "U"));
    }

    @Test
    public void testGetAllModelNames() {
        System.out.println("Названия моделей:");
        System.out.println(Arrays.toString(getMotorbike().getAllModelNames()));
    }

    @Test
    public void testGetAllModelPrices() {
        System.out.println("Цены моделей:");
        System.out.println(Arrays.toString(getMotorbike().getAllModelPrices()));
    }

    @Test
    public void testGetPriceByModelName() {
        final Motorbike newMotorbike = getMotorbike();
        try {
            assertEquals(newMotorbike.getPriceByModelName("Model 0"), 7000);
            assertEquals(newMotorbike.getPriceByModelName("Model 1"), 14000);
        } catch (NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        assertThrows(NoSuchModelNameException.class, () -> newMotorbike.getPriceByModelName("de Vil"));
    }

    @Test
    public void testSetPriceByModelName() {
        Motorbike newMotorbike = getMotorbike();
        try {
            newMotorbike.setPriceByModelName("Model 0", 50);
            newMotorbike.setPriceByModelName("Model 3", 888);
            assertEquals(newMotorbike.getPriceByModelName("Model 0"), 50.0);
            assertEquals(newMotorbike.getPriceByModelName("Model 3"), 888.0);
        } catch (NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        assertThrows(NoSuchModelNameException.class, () -> newMotorbike.setPriceByModelName("Summer", 20));
        assertThrows(ModelPriceOutOfBoundsException.class, () -> newMotorbike.setPriceByModelName("Model 0", -140));
    }

    @Test
    public void testAddModel() {
        Motorbike newMotorbike = getMotorbike();
        try {
            newMotorbike.addModel("King", 99999);
            newMotorbike.addModel("Frigg", 333);
            assertEquals(newMotorbike.getPriceByModelName("King"), 99999.0);
            assertEquals(newMotorbike.getPriceByModelName("Frigg"), 333.0);
        } catch (DuplicateModelNameException | NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        assertEquals(newMotorbike.getNumberOfModels(), 9);
        assertEquals(newMotorbike.getAllModelNames()[7], "King");
        assertEquals(newMotorbike.getAllModelNames()[8], "Frigg");
        assertThrows(DuplicateModelNameException.class, () -> newMotorbike.addModel("Model 0", 777));
        assertThrows(ModelPriceOutOfBoundsException.class, () -> newMotorbike.addModel("Nemesis", -3));
    }

    @Test
    public void testRemoveModel() {
        Motorbike newMotorbike = getMotorbike();
        try {
            newMotorbike.removeModel("Model 0");
            newMotorbike.removeModel("Model 2");
            newMotorbike.removeModel("Model 6");
            newMotorbike.removeModel("Model 1");
        } catch (NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        assertEquals(newMotorbike.getNumberOfModels(), 3);
        System.out.println("Названия моделей после удаления:");
        System.out.println(Arrays.toString(newMotorbike.getAllModelNames()));
        System.out.println("Цены после удаления моделей:");
        System.out.println(Arrays.toString(newMotorbike.getAllModelPrices()));
        assertThrows(NoSuchModelNameException.class, () -> newMotorbike.removeModel("Luke"));
    }
}
