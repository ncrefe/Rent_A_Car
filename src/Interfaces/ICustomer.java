package Interfaces;

import Enums.CustomerType;
import Enums.MemberType;

public interface ICustomer<T> {
    CustomerType getCustomerType();

    MemberType getMemberType();

    T getCustomerID();

}
