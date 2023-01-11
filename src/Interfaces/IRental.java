package Interfaces;

import Models.Car;
import Models.Customer;

public interface IRental {
    Customer getCustomer();

    Car getCar();

    double getPrice();

    int getDuration();

    int getRentalCode();
}
