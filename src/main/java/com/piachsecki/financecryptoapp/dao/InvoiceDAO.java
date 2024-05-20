package com.piachsecki.financecryptoapp.dao;

import com.piachsecki.financecryptoapp.domain.Customer;
import com.piachsecki.financecryptoapp.domain.Invoice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InvoiceDAO {
    Invoice findInvoiceById(Long customerId, Long invoiceId);
    List<Invoice> findAllInvoicesForCustomer(Long customerId);

    void addInvoice(Long customerId, Invoice invoice);
    void deleteInvoiceById(Long customerId, Long invoiceId);
    Invoice updateInvoice(Long customerId, Long invoiceId, Invoice invoice);
}
