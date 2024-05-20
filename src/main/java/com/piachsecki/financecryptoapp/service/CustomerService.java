package com.piachsecki.financecryptoapp.service;

import com.piachsecki.financecryptoapp.dao.CustomerDAO;
import com.piachsecki.financecryptoapp.domain.Customer;
import com.piachsecki.financecryptoapp.repository.CustomerRepository;
import com.piachsecki.financecryptoapp.rest.CurrencyRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements CustomerDAO {

    private CustomerRepository customerRepository;
    private CurrencyRateService rateService;

    @Override
    @Transactional
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
        Mono<String> ratesFromApi = rateService.getRatesFromApi();
        System.out.println(ratesFromApi);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);

    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer oldCustomer = customerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format(
                                "There is no customer with the given id:[%s]",
                                id))
                );
        return customerRepository.save(Customer.builder()
                .id(oldCustomer.getId())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .name(customer.getName())
                .email(customer.getEmail())
                .surname(customer.getSurname())
                .invoices(customer.getInvoices())
                .build()
        );


    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(new Customer());
    }
}
