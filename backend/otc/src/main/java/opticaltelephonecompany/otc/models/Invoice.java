package opticaltelephonecompany.otc.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceId;
    private String invoiceDate;
    private String amount;
    private boolean isPaid;
   
    @ManyToMany
    @JoinTable(name = "invoice_call", joinColumns = @JoinColumn(name = "invoice_id"), inverseJoinColumns = @JoinColumn(name = "call_id"))
    private Set<Call> calls = new HashSet<>();

    public Invoice(Long invoiceId, String invoiceDate, String amount, boolean isPaid, Set<Call> calls) {
        this.invoiceId = invoiceId;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
        this.isPaid = isPaid;
        this.calls = calls;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Invoice() {
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Set<Call> getCalls() {
        return calls;
    }

    public void setCalls(Set<Call> calls) {
        this.calls = calls;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", amount=" + amount + ", isPaid="
                + isPaid + ", calls=" + calls + "]";
    }

  
    
    
}
