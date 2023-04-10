package com.ceta.pojos;

import java.util.List;

public class UpdateCustomerResponse {
    private Customer customer;
    private List<Error> errors;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

}
