package opticaltelephonecompany.otc.models;


import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "call")
public class Call {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long callId;
    private String startTime;
    private String endTime;
    private String duration;
    private String costPerMinute;
    private String discountForCalls;
    private String vat;
    private String netCost;
    private String grossCost;
    private String totalCost;
    private String callDate;
    private boolean isPaid;
    private boolean isInvoiced;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "current_call_id")
    private CurrentCall currentCall;

    public Call() {
    }

    public Call(Long callId, String startTime, String endTime, String duration, String costPerMinute,
            String discountForCalls, String vat, String netCost, String grossCost, String totalCost, String callDate,
            boolean isPaid, boolean isInvoiced, Users user, CurrentCall currentCall, CallReceiver receiver) {
        this.callId = callId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.costPerMinute = costPerMinute;
        this.discountForCalls = discountForCalls;
        this.vat = vat;
        this.netCost = netCost;
        this.grossCost = grossCost;
        this.totalCost = totalCost;
        this.callDate = callDate;
        this.isPaid = isPaid;
        this.isInvoiced = isInvoiced;
        this.user = user;
        this.currentCall = currentCall;
        this.receiver = receiver;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean isInvoiced() {
        return isInvoiced;
    }

    public void setInvoiced(boolean isInvoiced) {
        this.isInvoiced = isInvoiced;
    }

 
    public String getCostPerMinute() {
        return costPerMinute;
    }


    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private CallReceiver receiver;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setCostPerMinute(String costPerMinute) {
        this.costPerMinute = costPerMinute;
    }

    public String getDiscountForCalls() {
        return discountForCalls;
    }

    public void setDiscountForCalls(String discountForCalls) {
        this.discountForCalls = discountForCalls;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getNetCost() {
        return netCost;
    }

    public void setNetCost(String netCost) {
        this.netCost = netCost;
    }

    public String getGrossCost() {
        return grossCost;
    }

    public void setGrossCost(String grossCost) {
        this.grossCost = grossCost;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public CallReceiver getReceiver() {
        return receiver;
    }

    public void setReceiver(CallReceiver receiver) {
        this.receiver = receiver;
    }

    public CurrentCall getCurrentCall() {
        return currentCall;
    }

    public void setCurrentCall(CurrentCall currentCall) {
        this.currentCall = currentCall;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Call [callId=" + callId + ", startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration
                + ", costPerMinute=" + costPerMinute + ", discountForCalls=" + discountForCalls + ", vat=" + vat
                + ", netCost=" + netCost + ", grossCost=" + grossCost + ", totalCost=" + totalCost + ", callDate="
                + callDate + ", isPaid=" + isPaid + ", isInvoiced=" + isInvoiced + ", user=" + user + ", currentCall="
                + currentCall + ", receiver=" + receiver + "]";
    }

    

  

}
