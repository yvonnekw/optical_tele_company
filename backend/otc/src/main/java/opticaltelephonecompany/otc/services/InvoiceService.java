package opticaltelephonecompany.otc.services;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import opticaltelephonecompany.otc.dto.InvoiceDTO;
import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.Invoice;

public interface InvoiceService {

    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(Long invoiceId);

   // Invoice createInvoice(Long invoiceId);

    Invoice updateInvoice(Long invoiceId, Invoice invoiceDetails);

    void deleteInvoice(Long invoiceId);

    Invoice createInvoice(Invoice invoice);

    List<InvoiceDTO> getAllInvoicesWithCallIds();

    //Invoice createInvoiceForCalls(Set<Call> calls);
    //Invoice createInvoiceForCalls(Invoice invoice);
    InvoiceDTO createInvoiceForCalls(InvoiceDTO invoiceDTO);

   // BigDecimal createInvoiceForCalls(Set<Call> calls);

   // List<Call> getAllPaidCalls();

   // List<Call> getAllUnpaidCalls();

   //void generateInvoiceForCalls(List<Call> calls);

   // void triggerInvoiceCreation(String username, InvoiceWithCallIdsDTO invoiceWithCallIdsDTO);
    //List<Invoice> getAllInvoices();

    //List<Invoice> findAllInvoicesWithCallsAndUser();

    //List<Invoice> findAllInvoiceCallsUser();

    ///List<Invoice> findAllInvoicesWithCallsAndUsers();
    
}
