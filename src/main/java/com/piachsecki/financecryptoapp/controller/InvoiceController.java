package com.piachsecki.financecryptoapp.controller;

import com.piachsecki.financecryptoapp.dao.InvoiceDAO;
import com.piachsecki.financecryptoapp.domain.Customer;
import com.piachsecki.financecryptoapp.domain.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/{customerId}/invoices")
public class InvoiceController {
    private final InvoiceDAO invoiceService;

    @GetMapping
    public ResponseEntity<?> getAllInvoicesForCustomer(@PathVariable Long customerId) {
        List<Invoice> allInvoicesForCustomer = invoiceService.findAllInvoicesForCustomer(customerId);
        return ResponseEntity.ok(allInvoicesForCustomer);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<?> getInvoiceByID(@PathVariable Long customerId, @PathVariable Long invoiceId) {
        Invoice invoiceById = invoiceService.findInvoiceById(customerId, invoiceId);
        return ResponseEntity.ok(invoiceById);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Void> deleteInvoiceById(@PathVariable Long customerId, @PathVariable Long invoiceId) {
        invoiceService.deleteInvoiceById(customerId, invoiceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createInvoice(@PathVariable Long customerId,@RequestBody Invoice invoice) {
        invoiceService.addInvoice(customerId, invoice);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{invoiceId}")
    public ResponseEntity<Invoice> updateInvoice(
            @PathVariable Long customerId, @PathVariable Long invoiceId,
            @RequestBody Invoice invoice) {
        Invoice updatedInvoice = invoiceService.updateInvoice(customerId, invoiceId, invoice);

        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }


}
