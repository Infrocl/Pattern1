package ru.ssau.studingJava;

import ru.ssau.studingJava.command.Command;
import ru.ssau.studingJava.exception.DuplicateModelNameException;
import ru.ssau.studingJava.exception.ModelPriceOutOfBoundsException;
import ru.ssau.studingJava.exception.NoSuchModelNameException;
import ru.ssau.studingJava.visitor.Visitor;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Car implements Vehicle, Cloneable {
    static class Model implements Cloneable, Serializable {
        String name;
        double price;

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public Model clone() {
            try {
                return (Model) super.clone();
            } catch (CloneNotSupportedException e) {
                return new Model(this.name, this.price);
            }
        }

        @Override
        public String toString() {
            return name + "; " + price;
        }
    }

    private String brand;
    private Model[] models;
    private Command command;

    public Car(String brand, int size) {
        this.brand = brand;
        models = new Model[size];
        for (int i = 0; i < size; i++) {
            models[i] = new Model("Model " + i, (i + 1) * 100000);
        }
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void print(Writer writer) {
        command.printCar(this, writer);
    }

    private class CarIterator implements Iterator<Model> {
        int currentPosition = 0;

        @Override
        public boolean hasNext() {
            return currentPosition < models.length;
        }

        @Override
        public Model next() {
            if (!hasNext()) {
                return null;
            }
            return models[currentPosition++];
        }
    }

    public Iterator iterator() {
        return new CarIterator();
    }

    private static class Memento {
        static byte[] state;

        static void setCar(Car car) {
            try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                 ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
                out.writeObject(car);
                state = byteOut.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Car getCar() {
            try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(state))) {
                return (Car) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return new Car("default", 1);
        }
    }

    public void createMemento() {
        Memento.setCar(this);
    }

    public void setMemento() {
        Car car = Memento.getCar();
        brand = car.brand;
        models = car.models;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Car clone() {
        try {
            Car car = (Car) super.clone();
            car.models = new Model[this.getNumberOfModels()];
            for (int i = 0; i < car.getNumberOfModels(); i++) {
                car.models[i] = this.models[i].clone();
            }
            return car;
        } catch (CloneNotSupportedException e) {
            return new Car(this.brand, this.getNumberOfModels());
        }
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setModelName(String prevName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        List<String> allModelsName = Arrays.asList(getAllModelNames());
        if (allModelsName.contains(newName)) {
            throw new DuplicateModelNameException();
        }
        int index = allModelsName.indexOf(prevName);
        if (index != -1) {
            models[index].name = newName;
        } else {
            throw new NoSuchModelNameException();
        }
    }

    @Override
    public String[] getAllModelNames() {
        String[] modelNames = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            modelNames[i] = models[i].name;
        }
        return modelNames;
    }

    @Override
    public double[] getAllModelPrices() {
        double[] modelPrices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            modelPrices[i] = models[i].price;
        }
        return modelPrices;
    }

    @Override
    public double getPriceByModelName(String modelName) throws NoSuchModelNameException {
        for (Model carModel : models) {
            if (carModel.name.equals(modelName)) {
                return carModel.price;
            }
        }
        throw new NoSuchModelNameException();
    }

    @Override
    public void setPriceByModelName(String modelName, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        List<String> allModelsName = Arrays.asList(getAllModelNames());
        int index = allModelsName.indexOf(modelName);
        if (index != -1) {
            models[index].price = price;
        } else {
            throw new NoSuchModelNameException();
        }
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        List<String> allModelNames = Arrays.asList(getAllModelNames());
        if (allModelNames.contains(name)) {
            throw new DuplicateModelNameException();
        }
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(name, price);
    }

    @Override
    public void removeModel(String name) throws NoSuchModelNameException {
        List<String> allModelNames = Arrays.asList(getAllModelNames());
        int index = allModelNames.indexOf(name);
        if (index != -1) {
            Model[] newModels = new Model[models.length - 1];
            System.arraycopy(models, 0, newModels, 0, index);
            System.arraycopy(models, index + 1, newModels, index, models.length - index - 1);
            models = newModels;
        } else {
            throw new NoSuchModelNameException();
        }
    }

    @Override
    public int getNumberOfModels() {
        return models.length;
    }
}
