package com.piachsecki.financecryptoapp.controller;

import com.piachsecki.financecryptoapp.dao.CustomerDAO;
import com.piachsecki.financecryptoapp.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerDAO customerService;


    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> allCustomers = customerService.findAllCustomers();
        return ResponseEntity.ok(allCustomers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerByID(@PathVariable Long id) {
        Customer customerById = customerService.findCustomerById(id);
        return ResponseEntity.ok(customerById);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerByID(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {
        Customer updateCustomer = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }


}
