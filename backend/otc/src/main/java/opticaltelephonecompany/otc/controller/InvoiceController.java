package opticaltelephonecompany.otc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import opticaltelephonecompany.otc.dto.InvoiceWithCallIdsDTO;
import opticaltelephonecompany.otc.models.Invoice;
import opticaltelephonecompany.otc.repository.InvoiceRepository;
import opticaltelephonecompany.otc.services.InvoiceService;

@RestController
@CrossOrigin("*")
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
/* 
@GetMapping("/get-all-invoice")
public ResponseEntity<List<Invoice>> getAllInvoices() {
List<Invoice> invoices = invoiceService.getAllInvoices();
return ResponseEntity.ok(invoices);
}
*/
  /* 
  @GetMapping("/get-all-invoice")
  public ResponseEntity<?> getAllInvoices() {
  try {
    List<Invoice> invoices = invoiceService.getAllInvoices();
    return ResponseEntity.ok(invoices);
  } catch (Exception e) {
    // Log the exception for debugging purposes
    e.printStackTrace();
    // Return an error response entity
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An error occurred while fetching invoices.");
  }
  }*/
  
  @GetMapping("/all")
  public List<Invoice> getAllInvoices() {
      return invoiceRepository.findAll();
  }

    @GetMapping("/get-all-invoice")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllInvoicesWithCallIds() {
        try {
            List<InvoiceWithCallIdsDTO> invoicesWithCallIds = invoiceService.getAllInvoicesWithCallIds();
            return ResponseEntity.ok(invoicesWithCallIds);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return an error response entity
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching invoices with call IDs.");
        }
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long invoiceId) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create-invoice")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.ok(createdInvoice);
    }
/* 
    @PutMapping("/{invoiceId}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long invoiceId, @RequestBody Invoice invoiceDetails) {
        Invoice updatedInvoice = invoiceService.updateInvoice(invoiceId, invoiceDetails);
        return ResponseEntity.ok(updatedInvoice);
    }*/

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return ResponseEntity.noContent().build();
    }
    
}
