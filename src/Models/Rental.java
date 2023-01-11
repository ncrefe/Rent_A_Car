package Models;

import Enums.CustomerType;
import Enums.ModelYearRatio;
import Interfaces.IRental;
import Models.exception.CustomError;
import Models.exception.CustomException;

import java.util.Random;

public class Rental implements IRental {
    private final Customer customer;
    private final Car car;
    private final int rentalCode;
    private final int duration;
    private final double price;


    public Rental(Customer customer, Car car, int duration) throws CustomException {
        this.customer = customer;
        this.car = car;
        this.duration = duration;
        this.rentalCode = generateRentalCode();
        this.price = totalPrice();

    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public Car getCar() {
        return car;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    private int generateRentalCode() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    private double getModelYearRatio() throws CustomException {
        int carModelYear = car.getCarModelYear();

        if (carModelYear >= 2022) {
            return ModelYearRatio.YEAR_2022.getModelYearRatio();
        } else if (carModelYear >= 2020 && carModelYear <= 2021) {
            return ModelYearRatio.YEAR_2020_2021.getModelYearRatio();
        } else if (carModelYear <= 2019 && carModelYear >= 2017) {
            return ModelYearRatio.YEAR_2017_2019.getModelYearRatio();
        }

        throw new CustomException(CustomError.YEAR_NOT_APPROPRIATE, "Year is not appropriate");
    }

    private double calculateDailyPrice() throws CustomException {
        return car.getBasePrice() * getModelYearRatio();
    }

    private double calculateMonthlyPrice() throws CustomException {
        return 30 * calculateDailyPrice();
    }

    private double calculatePriceWithoutDiscount() throws CustomException {
        if (customer.getCustomerType() == CustomerType.INDIVIDUAL) {
            return calculateDailyPrice();
        } else if (customer.getCustomerType() == CustomerType.COMMERCIAL) {
            return calculateMonthlyPrice();
        }
        return -1;
    }

    private double applyDiscount(double price, double ratio) {
        double discountedPriceRatio = 1.0 - (ratio / 100);
        return price * discountedPriceRatio;
    }


    // Main price for Individual
    private double calculateIndividualCustomerDiscount() throws CustomException {
        return switch (customer.getMemberType()) {
            case MEMBER -> applyDiscount(calculatePriceWithoutDiscount(), 10);
            case NON_MEMBER -> calculatePriceWithoutDiscount();
            default -> -1;
        };
    }

    // Main price for Commercial
    private double calculateCommercialCustomerDiscount() throws CustomException {
        return switch (customer.getMemberType()) {
            case SILVER_MEMBER -> applyDiscount(calculatePriceWithoutDiscount(), 20);
            case GOLD_MEMBER -> applyDiscount(calculatePriceWithoutDiscount(), 25);
            case PLATINUM_MEMBER -> applyDiscount(calculatePriceWithoutDiscount(), 30);
            default -> -1;
        };
    }

    private double totalPrice() throws CustomException {
        if (customer.getCustomerType() == CustomerType.INDIVIDUAL) {
            return calculateIndividualCustomerDiscount() * duration;
        } else if (customer.getCustomerType() == CustomerType.COMMERCIAL) {
            return calculateCommercialCustomerDiscount() * duration;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Rental{" +
                "customer=" + customer +
                ", car=" + car +
                ", rentalCode=" + rentalCode +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }

    @Override
    public int getRentalCode() {
        return rentalCode;
    }
}
