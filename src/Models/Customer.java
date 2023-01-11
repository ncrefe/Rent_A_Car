package Models;

import Enums.CustomerType;
import Enums.MemberType;
import Interfaces.ICustomer;

public abstract class Customer<T> implements ICustomer<T> {
    private final CustomerType customerType;
    private final MemberType memberType;
    private final T customerID;

    protected Customer(CustomerType customerType, MemberType memberType, T customerID) {
        this.customerType = customerType;
        this.memberType = memberType;
        this.customerID = customerID;
    }

    @Override
    public CustomerType getCustomerType() {
        return customerType;
    }

    @Override
    public MemberType getMemberType() {
        return memberType;
    }

    @Override
    public T getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerType=" + customerType +
                ", memberType=" + memberType +
                ", customerID=" + customerID +
                '}';
    }
}
