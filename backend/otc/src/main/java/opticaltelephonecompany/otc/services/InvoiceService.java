package opticaltelephonecompany.otc.services;


import java.util.List;

import opticaltelephonecompany.otc.dto.InvoiceWithCallIdsDTO;
import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.Invoice;

public interface InvoiceService {

    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(Long invoiceId);

   // Invoice createInvoice(Long invoiceId);

    Invoice updateInvoice(Long invoiceId, Invoice invoiceDetails);

    void deleteInvoice(Long invoiceId);

    Invoice createInvoice(Invoice invoice);

    List<InvoiceWithCallIdsDTO> getAllInvoicesWithCallIds();

    List<Call> getAllPaidCalls();

    List<Call> getAllUnpaidCalls();

   void generateInvoiceForCalls(List<Call> calls);

   // void triggerInvoiceCreation(String username, InvoiceWithCallIdsDTO invoiceWithCallIdsDTO);
    //List<Invoice> getAllInvoices();

    //List<Invoice> findAllInvoicesWithCallsAndUser();

    //List<Invoice> findAllInvoiceCallsUser();

    ///List<Invoice> findAllInvoicesWithCallsAndUsers();
    
}
