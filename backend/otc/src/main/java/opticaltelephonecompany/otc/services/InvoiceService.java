package opticaltelephonecompany.otc.services;


import java.util.List;

import opticaltelephonecompany.otc.models.Invoice;
import opticaltelephonecompany.otc.models.InvoiceWithCallIdsDTO;

public interface InvoiceService {

    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(Long invoiceId);

   // Invoice createInvoice(Long invoiceId);

    Invoice updateInvoice(Long invoiceId, Invoice invoiceDetails);

    void deleteInvoice(Long invoiceId);

    Invoice createInvoice(Invoice invoice);

    List<InvoiceWithCallIdsDTO> getAllInvoicesWithCallIds();
    
}
