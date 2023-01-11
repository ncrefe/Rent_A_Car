package Models;

import Enums.CustomerType;
import Enums.MemberType;
import Interfaces.IRentACarCompany;
import Processors.DataProcessor;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RentACarCompany implements IRentACarCompany {
    final ArrayList<Rental> rentals;

    public RentACarCompany() throws FileNotFoundException {
        DataProcessor dataProcessor = new DataProcessor();
        rentals = dataProcessor.processData();
    }

    @Override
    public void work() {
        // Total number of cars rented
        totalNumberOfCarsRented();
        // Total number of commercial rentals
        totalNumberOfCommercialRentals();
        // Total number of commercial rental-month
        totalNumberOfCommercialRentalMonth();
        // Total number of individual rentals
        totalNumberOfIndividualRentals();
        // Total number of individual rental-day
        totalNumberOfIndividualRentalDay();
        // Total number of rentals of individual non-member customers
        totalNumberOfRentalsOfIndividualNonMemberCustomers();
        // Total number of rentals of individual member customers
        totalNumberOfRentalsOfIndividualMemberCustomers();
        // Total number of rentals of silver commercial customers
        totalNumberOfRentalsOfSilverCommercialCustomers();
        // Total number of rentals of gold commercial customers
        totalNumberOfRentalsOfGoldCommercialCustomers();
        // Total number of rentals of platinum commercial customers
        totalNumberOfRentalsOfPlatinumCommercialCustomers();

        // Individual Rentals
        showIndividualRentals();
        //  Commercial Rentals
        showCommercialRentals();

    }

    private void totalNumberOfCarsRented() {
        System.out.println("Total number of cars rented: " + rentals.size());
    }

    private void totalNumberOfCommercialRentals() {
        int count = 0;

        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerType() == CustomerType.COMMERCIAL) {
                count++;
            }
        }
        System.out.println("Total number of commercial rentals: " + count);
    }

    private void totalNumberOfCommercialRentalMonth() {
        int total = 0;

        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerType() == CustomerType.COMMERCIAL) {
                total += rental.getDuration();
            }
        }

        System.out.println("Total number of commercial rental-month: " + total);
    }

    private void totalNumberOfIndividualRentals() {
        int count = 0;

        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerType() == CustomerType.INDIVIDUAL) {
                count++;
            }
        }
        System.out.println("Total number of individual rentals: " + count);
    }

    private void totalNumberOfIndividualRentalDay() {
        int total = 0;

        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerType() == CustomerType.INDIVIDUAL) {
                total += rental.getDuration();
            }
        }
        System.out.println("Total number of individual rental-day: " + total);
    }

    private void totalNumberOfRentalsOfIndividualNonMemberCustomers() {
        int count = 0;

        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerType() == CustomerType.INDIVIDUAL &&
                    rental.getCustomer().getMemberType() == MemberType.NON_MEMBER) {
                count++;
            }
        }

        System.out.println("Total number of rentals of individual non-member customers: " + count);
    }

    private void totalNumberOfRentalsOfIndividualMemberCustomers() {
        int count = 0;

        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerType() == CustomerType.INDIVIDUAL &&
                    rental.getCustomer().getMemberType() == MemberType.MEMBER) {
                count++;
            }
        }

        System.out.println("Total number of rentals of individual member customers: " + count);
    }

    private void totalNumberOfRentalsOfSilverCommercialCustomers() {
        int count = 0;

        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerType() == CustomerType.COMMERCIAL &&
                    rental.getCustomer().getMemberType() == MemberType.SILVER_MEMBER) {
                count++;
            }
        }

        System.out.println("Total number of rentals of silver commercial customers: " + count);
    }

    private void totalNumberOfRentalsOfGoldCommercialCustomers() {
        int count = 0;

        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerType() == CustomerType.COMMERCIAL &&
                    rental.getCustomer().getMemberType() == MemberType.GOLD_MEMBER) {
                count++;
            }
        }

        System.out.println("Total number of rentals of gold commercial customers: " + count);
    }

    private void totalNumberOfRentalsOfPlatinumCommercialCustomers() {
        int count = 0;

        for (Rental rental : rentals) {
            if (rental.getCustomer().getCustomerType() == CustomerType.COMMERCIAL &&
                    rental.getCustomer().getMemberType() == MemberType.PLATINUM_MEMBER) {
                count++;
            }
        }

        System.out.println("Total number of rentals of platinum commercial customers: " + count);
    }

    private void showIndividualRentals() {
        System.out.println("\n\nIndividual Rentals:");
        System.out.println("No  Rental Code  Customer ID    isMember  Number of Days     Car Model       Model Year    Rental Price");
        int printIndex = 1;
        for (int index = 0; index < rentals.size(); index++) {
            Rental rental = rentals.get(index);
            if (rental.getCustomer().getCustomerType() == CustomerType.INDIVIDUAL) {
                System.out.print(pad(printIndex, 5));
                System.out.print(pad(rental.getRentalCode(), 13));
                System.out.print(pad(rental.getCustomer().getCustomerID(), 15));
                System.out.print(pad(rental.getCustomer().getMemberType().equals(MemberType.MEMBER), 15));
                System.out.print(pad(rental.getDuration(), 13));
                System.out.print(pad(rental.getCar().getModel(), 20));
                System.out.print(pad(rental.getCar().getModelYear(), 10));
                System.out.println(pad(decimalFormat(rental.getPrice()), 20));
                printIndex++;
            }
        }
    }

    private void showCommercialRentals() {
        System.out.println("\n\nCommercial Rentals:");
        System.out.println("No  Rental Code  Customer ID   Customer Type       Number of Months     Car Model             " +
                "Model Year     Rental Price");
        int printIndex = 1;

        for (int index = 0; index < rentals.size(); index++) {
            Rental rental = rentals.get(index);
            if (rental.getCustomer().getCustomerType() == CustomerType.COMMERCIAL) {
                System.out.print(pad(printIndex, 5) );
                System.out.print(pad(rental.getRentalCode(), 12));
                System.out.print(pad(rental.getCustomer().getCustomerID(), 15));
                System.out.print(pad(memberTypeFormatter(rental.getCustomer().getMemberType()), 25));
                System.out.print(pad(rental.getDuration(), 15));
                System.out.print(pad(rental.getCar().getModel(), 25));
                System.out.print(pad(rental.getCar().getModelYear(), 12));
                System.out.println(pad(decimalFormat(rental.getPrice()), 20));
                printIndex++;
            }
        }
    }

    private String memberTypeFormatter(MemberType memberType) {
        String formattedMemberType = memberType.toString();
        String[] parsedMemberType = formattedMemberType.split("_");
        formattedMemberType = parsedMemberType[0].toLowerCase(Locale.ENGLISH).substring(0, 1).toUpperCase() +
                parsedMemberType[0].toLowerCase(Locale.ENGLISH).substring(1);
        return formattedMemberType;
    }

    private <T> String pad(T text, int length) {
        return String.format("%-" + length + "." + length + "s", text.toString());
    }

    private String decimalFormat(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("00.00");
        return decimalFormat.format(number).replace(",", ".");
    }
}

