package Models;

import Interfaces.ICar;

public class Car implements ICar {
    private final String carModel;
    private final Integer carModelYear;
    private final Double basePrice;

    public Car(String carModel, Integer carModelYear, Double basePrice) {
        this.carModel = carModel;
        this.carModelYear = carModelYear;
        this.basePrice = basePrice;
    }

    @Override
    public Integer getCarModelYear() {
        return carModelYear;
    }

    @Override
    public Double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carModel='" + carModel + '\'' +
                ", carModelYear=" + carModelYear +
                ", basePrice=" + basePrice +
                '}';
    }

    @Override
    public String getModel() {
        return carModel;
    }

    @Override
    public Integer getModelYear() {
        return carModelYear;
    }

}
