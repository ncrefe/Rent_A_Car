package Processors;

import Enums.CustomerType;
import Enums.MemberType;
import Models.*;
import Models.exception.CustomError;
import Models.exception.CustomException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

public class DataProcessor {

    public ArrayList<Rental> processData() throws FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor();

        ArrayList<Rental> rentals = new ArrayList<>();
        ArrayList<String[]> lines = fileProcessor.scan();

        for (String[] line : lines) {
            Rental rental = createRental(line);
            if (rental != null)
                rentals.add(rental);
        }

        return rentals;
    }

    private Rental createRental(String[] line) {
        try {
            Customer customer;

            if (CustomerType.valueOf(line[0].toUpperCase(Locale.ENGLISH)) == CustomerType.INDIVIDUAL) {
                if (createMemberType(line[1]) == MemberType.NON_MEMBER) {
                    customer = new IndividualCustomer<Long>(CustomerType.valueOf(line[0].toUpperCase(Locale.ENGLISH)), createMemberType(line[1]),
                            line[1].equals("") ? null : Long.parseLong(line[1]));
                } else {
                    customer = new IndividualCustomer<String>(CustomerType.valueOf(line[0].toUpperCase(Locale.ENGLISH)), createMemberType(line[1]),
                            line[1]);
                }
            } else {
                customer = new CommercialCustomer<String>(CustomerType.valueOf(line[0].toUpperCase(Locale.ENGLISH)), createMemberType(line[1]),
                        line[1]);
            }

            Car car = new Car(line[3], line[4].equals("") ? null : Integer.parseInt(line[4]),
                    line[5].equals("") ? null : Double.parseDouble(line[5]));

            Rental rental = new Rental(customer, car, line[2].equals("") ? null : Integer.parseInt(line[2]));

            return rental;
        } catch (CustomException e){
            System.out.println(e);
        }

        return null;
    }

    private boolean lengthCheck(String customerId, int length) {
        return customerId.length() == length;
    }

    private boolean firstLetterCheck(String customerId, String letter) {
        return customerId.substring(0, 1).equals(letter);
    }

    private boolean memberTypeCheck(String customerId, int length, String letter) {
        return firstLetterCheck(customerId, letter) && lengthCheck(customerId, length);
    }

    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean nonMemberTypeCheck(String customerId, int length) {
        return lengthCheck(customerId, length) && isNumeric(customerId);
    }

    private MemberType createMemberType(String customerId) throws CustomException {
        if (memberTypeCheck(customerId, 12, "M")) {
            return MemberType.MEMBER;
        } else if (nonMemberTypeCheck(customerId, 11)) {
            return MemberType.NON_MEMBER;
        } else if (memberTypeCheck(customerId, 8, "S")) {
            return MemberType.SILVER_MEMBER;
        } else if (memberTypeCheck(customerId, 8, "G")) {
            return MemberType.GOLD_MEMBER;
        } else if (memberTypeCheck(customerId, 8, "P")) {
            return MemberType.PLATINUM_MEMBER;
        }

        throw new CustomException(CustomError.CUSTOMER_ID_NOT_APPROPRIATE, customerId + " is not appropriate");
    }
}
