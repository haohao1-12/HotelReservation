package service;

import model.Customer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static CustomerService customerService;
    private CustomerService(){}

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }
    static Map<String, Customer> mapOfCustomer = new HashMap<String, Customer>();
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        mapOfCustomer.put(customer.getEmail(), customer);
    }

    public Customer getCustomer(String customerEmail) {
        return mapOfCustomer.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return mapOfCustomer.values();
    }
}
