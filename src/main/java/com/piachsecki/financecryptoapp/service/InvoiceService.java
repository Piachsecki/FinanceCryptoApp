package com.piachsecki.financecryptoapp.service;

import com.piachsecki.financecryptoapp.dao.InvoiceDAO;
import com.piachsecki.financecryptoapp.domain.Customer;
import com.piachsecki.financecryptoapp.domain.Invoice;
import com.piachsecki.financecryptoapp.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class InvoiceService implements InvoiceDAO {
    private final InvoiceRepository invoiceRepository;
    private final CustomerService customerService;
    @Override
    public Invoice findInvoiceById(Long customerId, Long invoiceId) {
        if(Objects.isNull(customerService.findCustomerById(customerId))){
            throw new RuntimeException(String.format("User with given id: [%s] does not exist", customerId));
        }
        return invoiceRepository.findById(invoiceId).orElse(new Invoice());
    }

    @Override
    public List<Invoice> findAllInvoicesForCustomer(Long customerId) {
        return invoiceRepository.findAllByCustomerId(customerId);
    }

    @Override
    @Transactional
    public void addInvoice(Long customerId, Invoice invoice) {
        Customer customerById = customerService.findCustomerById(customerId);
        if(Objects.isNull(customerById)){
            throw new RuntimeException(String.format("User with given id: [%s] does not exist", customerId));
        }
        invoice.setCustomer(customerById);
        customerById.getInvoices().add(invoice);
        customerService.updateCustomer(customerId, customerById);
        invoiceRepository.save(invoice);

    }

    @Override
    public void deleteInvoiceById(Long customerId, Long invoiceId) {
        if(Objects.isNull(customerService.findCustomerById(customerId))){
            throw new RuntimeException(String.format("User with given id: [%s] does not exist", customerId));
        }
        invoiceRepository.deleteById(invoiceId);
    }

    @Override
    public Invoice updateInvoice(Long customerId, Long invoiceId, Invoice newInvoice) {
        Customer customerById = customerService.findCustomerById(customerId);
        if(Objects.isNull(customerById)){
            throw new RuntimeException(String.format("User with given id: [%s] does not exist", customerId));
        }
        Invoice invoiceToUpdate = invoiceRepository.findById(invoiceId).orElseThrow(
                () -> new RuntimeException(
                        String.format(
                                "You cannot update the non existing invoice! [%s]", invoiceId))
        );
        invoiceToUpdate.setCostInvoice(newInvoice.isCostInvoice());
        invoiceToUpdate.setDate(newInvoice.getDate());
        invoiceToUpdate.setAmount(newInvoice.getAmount());
        return invoiceRepository.save(invoiceToUpdate);
    }
}
