package com.piachsecki.financecryptoapp.dao;

import com.piachsecki.financecryptoapp.domain.Customer;

import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    void deleteCustomer(Long id);
    Customer updateCustomer(Long id, Customer customer);
    List<Customer> findAllCustomers();
    Customer findCustomerById(Long id);
}
