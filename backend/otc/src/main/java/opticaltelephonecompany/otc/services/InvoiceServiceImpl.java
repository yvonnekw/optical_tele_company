package opticaltelephonecompany.otc.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import opticaltelephonecompany.otc.dto.InvoiceWithCallIdsDTO;
import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.Invoice;
import opticaltelephonecompany.otc.repository.CallRepository;
import opticaltelephonecompany.otc.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

      private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final InvoiceRepository invoiceRepository;
    private final CallRepository callRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CallRepository callRepository) {
        this.invoiceRepository = invoiceRepository;
        this.callRepository = callRepository;
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

    /* 
    @Override
    public Invoice createInvoice(Invoice invoice) {
        // You may want to add additional logic here before saving the invoice
        return invoiceRepository.save(invoice);
    }
    */

    @Override
    public Invoice createInvoice(Invoice invoice) {
 
        Invoice savedInvoice = invoiceRepository.save(invoice);

        Set<Call> calls = savedInvoice.getCalls();

        for (Call call : calls) {
            call.setInvoiced(true);
            callRepository.save(call);
        }

        return savedInvoice;
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
                List<Long> callIds = invoice.getCalls().stream().map(call -> call.getCallId())
                        .collect(Collectors.toList());
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

    public List<Call> getAllPaidCalls() {
        List<Call> paidCalls = new ArrayList<>();
        List<Invoice> paidInvoices = invoiceRepository.findByIsPaidTrue();

        for (Invoice invoice : paidInvoices) {
            for (Call call : invoice.getCalls()) {
                if (call.isPaid()) { // Check the payment status of the call
                    paidCalls.add(call);
                }
            }
        }
        return paidCalls;
    }

    public List<Call> getAllUnpaidCalls() {
        List<Call> unpaidCalls = new ArrayList<>();
        List<Invoice> unpaidInvoices = invoiceRepository.findByIsPaidFalse();

        for (Invoice invoice : unpaidInvoices) {
            for (Call call : invoice.getCalls()) {
                if (!call.isPaid()) { // Check the payment status of the call
                    unpaidCalls.add(call);
                }
            }
        }
        logger.info("Unpaid Calls: {}", unpaidCalls);
        return unpaidCalls;
    }

    /* 
    public List<Call> getAllPaidCalls() {
        List<Invoice> paidInvoices = invoiceRepository.findByIsPaidTrue();
        List<Call> paidCalls = new ArrayList<>();
        for (Invoice invoice : paidInvoices) {
            paidCalls.addAll(invoice.getCalls());
        }
        return paidCalls;
    }

    public List<Call> getAllUnpaidCalls() {
        List<Invoice> unpaidInvoices = invoiceRepository.findByIsPaidFalse();
        List<Call> unpaidCalls = new ArrayList<>();
        for (Invoice invoice : unpaidInvoices) {
            unpaidCalls.addAll(invoice.getCalls());
        }
        return unpaidCalls;
    }
*/
    
  public void triggerInvoiceCreation(String username, InvoiceWithCallIdsDTO invoiceWithCallIdsDTO) {
    // Log that the method is triggered
    logger.info("Triggering invoice creation for user: {}", username);

    List<Call> calls = callRepository.findByUserUsername(username);
    if (calls.size() >= 5) {
        // Check if the call IDs have already been included in previous invoices
        List<Long> callIdsIncludedInPreviousInvoices = callRepository.findCallIdsIncludedInInvoice(username);
        List<Call> callsToAddToInvoice = new ArrayList<>();
        for (Call call : calls) {
            if (!callIdsIncludedInPreviousInvoices.contains(call.getCallId())) {
                callsToAddToInvoice.add(call);
            }
        }
        if (!callsToAddToInvoice.isEmpty()) {
            // Create a new invoice and associate it with the calls
            Invoice invoice = new Invoice();
            invoice.setInvoiceDate(invoiceWithCallIdsDTO.getInvoiceDate()); // Provide the appropriate invoice date
            invoice.setAmount(invoiceWithCallIdsDTO.getAmount());
            //invoice.setPaid(invoiceWithCallIdsDTO.isPaid()); // Initially set as unpaid
            invoice.setCalls(new HashSet<>(callsToAddToInvoice));
            invoiceRepository.save(invoice); // Save the invoice to the database

            // Log that the invoice is created
            logger.info("Invoice created for user: {}. Number of calls included: {}", username, callsToAddToInvoice.size());
        }
    }
    }
    //public List<Invoice> getAllInvoices() {
       // return invoiceRepository.findAll();
   // }

@Override
public void generateInvoiceForCalls(List<Call> calls) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'generateInvoiceForCalls'");
}

/* 
    @Override
    public List<Invoice> findAllInvoicesWithCallsAndUser() {
       return invoiceRepository.findAllInvoicesWithCallsAndUser();
    }*/
    
}
