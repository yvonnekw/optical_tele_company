package opticaltelephonecompany.otc.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long paymentId;
    @Column(name = "amount")
    private String amount;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    private String fullNameOnPaymentCard;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    // Constructors, Getters, and Setters
    // Omitted for brevity

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getFullNameOnPaymentCard() {
        return fullNameOnPaymentCard;
    }

    public void setFullNameOnPaymentCard(String fullNameOnPaymentCard) {
        this.fullNameOnPaymentCard = fullNameOnPaymentCard;
    }

    public Set<Call> getCalls() {
        return calls;
    }

    public void setCalls(Set<Call> calls) {
        this.calls = calls;
    }

    @ManyToMany(mappedBy = "payments")
    private Set<Call> calls = new HashSet<>();

    public Payment(Long paymentId, String amount, LocalDateTime paymentDate, String fullNameOnPaymentCard, Users user,
            Set<Call> calls) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.fullNameOnPaymentCard = fullNameOnPaymentCard;
        this.user = user;
        this.calls = calls;
    }

    public Payment() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", paymentDate=" + paymentDate
                + ", fullNameOnPaymentCard=" + fullNameOnPaymentCard + ", user=" + user + ", calls=" + calls + "]";
    }

    
}
