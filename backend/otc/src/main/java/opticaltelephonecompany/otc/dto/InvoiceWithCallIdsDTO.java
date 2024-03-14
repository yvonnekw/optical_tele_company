package opticaltelephonecompany.otc.dto;

import java.util.List;

public class InvoiceWithCallIdsDTO {
    
    private Long invoiceId;
    private String invoiceDate;
    private String amount;
    private List<Long> callIds;
    private boolean isPaid;

    
    public InvoiceWithCallIdsDTO(Long invoiceId, String invoiceDate, String amount, List<Long> callIds,
            boolean isPaid) {
        this.invoiceId = invoiceId;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
        this.callIds = callIds;
        this.isPaid = isPaid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public InvoiceWithCallIdsDTO() {
    }
    

    public Long getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public List<Long> getCallIds() {
        return callIds;
    }

    public void setCallIds(List<Long> callIds) {
        this.callIds = callIds;
    }

    @Override
    public String toString() {
        return "InvoiceWithCallIdsDTO [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", amount=" + amount
                + ", callIds=" + callIds + ", isPaid=" + isPaid + "]";
    }
    
    
}
