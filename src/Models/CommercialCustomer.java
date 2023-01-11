package Models;

import Enums.CustomerType;
import Enums.MemberType;

public class CommercialCustomer<T> extends Customer {

    public CommercialCustomer(CustomerType customerType, MemberType memberType, T customerID) {
        super(customerType, memberType, customerID);
    }
}
