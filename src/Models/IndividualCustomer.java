package Models;

import Enums.CustomerType;
import Enums.MemberType;

public class IndividualCustomer<T> extends Customer<T> {

    public IndividualCustomer(CustomerType customerType, MemberType memberType, T customerID) {
        super(customerType, memberType, customerID);
    }


}
