package opticaltelephonecompany.otc.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
    private String totalTime;
    private String costPerMinute;
    private String discountForCalls;
    private String signUpDiscount;
    private String vat;
    private String netCost;
    private String grossCost;
    private String totalCost;

    public Call(Long callId, String startTime, String endTime, String duration, String totalTime, String costPerMinute,
            String discountForCalls, String signUpDiscount, String vat, String netCost, String grossCost,
            String totalCost, CallUser callUser) {
        this.callId = callId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.totalTime = totalTime;
        this.costPerMinute = costPerMinute;
        this.discountForCalls = discountForCalls;
        this.signUpDiscount = signUpDiscount;
        this.vat = vat;
        this.netCost = netCost;
        this.grossCost = grossCost;
        this.totalCost = totalCost;
        this.callUser = callUser;
    }

 

    @OneToOne(fetch=FetchType.EAGER)
    @JoinTable(                                                                                                                     
        name="user_call_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="call_id")}
    )
    private CallUser callUser;

    public Long getCallId() {
        return callId;
    }

    public CallUser getCallUser() {
        return callUser;
    }

    public void setCallUser(CallUser callUser) {
        this.callUser = callUser;
    }

    public Call(String startTime, String endTime, String duration, CallUser callUser) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.callUser = callUser;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getStartTime() {
        return startTime;
    }

    public Call() {
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getCostPerMinute() {
        return costPerMinute;
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

    public String getSignUpDiscount() {
        return signUpDiscount;
    }

    public void setSignUpDiscount(String signUpDiscount) {
        this.signUpDiscount = signUpDiscount;
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

    @Override
    public String toString() {
        return "Call [callId=" + callId + ", startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration
                + ", totalTime=" + totalTime + ", costPerMinute=" + costPerMinute + ", discountForCalls="
                + discountForCalls + ", signUpDiscount=" + signUpDiscount + ", vat=" + vat + ", netCost=" + netCost
                + ", grossCost=" + grossCost + ", totalCost=" + totalCost + ", callUser=" + callUser + "]";
    }

   



    

   







}
