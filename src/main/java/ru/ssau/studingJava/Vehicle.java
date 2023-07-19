package ru.ssau.studingJava;

import ru.ssau.studingJava.exception.DuplicateModelNameException;
import ru.ssau.studingJava.exception.NoSuchModelNameException;
import ru.ssau.studingJava.visitor.Visitor;

import java.io.Serializable;

public interface Vehicle extends Serializable {
    String getBrand();

    void setBrand(String brand);

    void setModelName(String prevName, String newName) throws DuplicateModelNameException, NoSuchModelNameException;

    String[] getAllModelNames();

    double[] getAllModelPrices();

    double getPriceByModelName(String modelName) throws NoSuchModelNameException;

    void setPriceByModelName(String modelName, double price) throws NoSuchModelNameException;

    void addModel(String name, double price) throws DuplicateModelNameException;

    void removeModel(String name) throws NoSuchModelNameException;

    int getNumberOfModels();

    void accept(Visitor visitor);
}
