package opticaltelephonecompany.otc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.Invoice;
import opticaltelephonecompany.otc.models.InvoiceWithCallIdsDTO;
import opticaltelephonecompany.otc.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        try {
            return invoiceRepository.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // You can throw a custom exception or return an empty list based on your
            // application logic
            throw new RuntimeException("An error occurred while fetching invoices.", e);
        }
    }

    @Override
    public Invoice getInvoiceById(Long invoiceId) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        return invoiceOptional.orElse(null); // Return null if invoice is not found
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        // You may want to add additional logic here before saving the invoice
        return invoiceRepository.save(invoice);
    }

    
    @Override
    public Invoice updateInvoice(Long invoiceId, Invoice invoiceDetails) {
        // Check if the invoice exists
        Invoice existingInvoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        // Update the existing invoice with the details provided
        existingInvoice.setAmount(invoiceDetails.getAmount());
        // Update other fields as needed

        // Save and return the updated invoice
        return invoiceRepository.save(existingInvoice);
    }

    @Override
    public void deleteInvoice(Long invoiceId) {
        // Check if the invoice exists
        Invoice existingInvoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        // Delete the invoice
        invoiceRepository.delete(existingInvoice);
    }
    
    @Override
    public List<InvoiceWithCallIdsDTO> getAllInvoicesWithCallIds() {
        try {
            List<Invoice> invoices = invoiceRepository.findAll();
            List<InvoiceWithCallIdsDTO> dtos = new ArrayList<>();

            for (Invoice invoice : invoices) {
                List<Long> callIds = invoice.getCalls().stream().map(call -> call.getCallId()).collect(Collectors.toList());
                InvoiceWithCallIdsDTO dto = new InvoiceWithCallIdsDTO();
                dto.setInvoiceId(invoice.getInvoiceId());
                dto.setInvoiceDate(invoice.getInvoiceDate());
                dto.setAmount(invoice.getAmount());
                dto.setCallIds(callIds);
                dtos.add(dto);
            }

            return dtos;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // You can throw a custom exception or return an empty list based on your
            // application logic
            throw new RuntimeException("An error occurred while fetching invoices with call IDs.", e);
        }
    }
    
}
