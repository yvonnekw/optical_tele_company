package opticaltelephonecompany.otc.event;

import opticaltelephonecompany.otc.dto.InvoiceWithCallIdsDTO;

public class InvoiceGenerationEvent {

    private String username;
    private InvoiceWithCallIdsDTO invoiceWithCallIdsDTO;

    public InvoiceGenerationEvent() {
    }

    public InvoiceGenerationEvent(String username, InvoiceWithCallIdsDTO invoiceWithCallIdsDTO) {
        this.username = username;
        this.invoiceWithCallIdsDTO = invoiceWithCallIdsDTO;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InvoiceWithCallIdsDTO getInvoiceWithCallIdsDTO() {
        return invoiceWithCallIdsDTO;
    }

    public void setInvoiceWithCallIdsDTO(InvoiceWithCallIdsDTO invoiceWithCallIdsDTO) {
        this.invoiceWithCallIdsDTO = invoiceWithCallIdsDTO;
    }
    
}
