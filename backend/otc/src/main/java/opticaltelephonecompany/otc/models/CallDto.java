package opticaltelephonecompany.otc.models;

import java.time.LocalDateTime;
import java.util.Set;

public class CallDto {

    private String startTime;
    private String endTime;
    private String duration;
    private String costPerMinute;
    private String discountForCalls;
    private String vat;
    private String netCost;
    private String grossCost;
    private String totalCost;
    private LocalDateTime callDate;
    private CallReceiver callReceiver;
    private Set<Users> callUsers;

    public CallDto(String startTime, String endTime, String duration, String costPerMinute, String discountForCalls,
            String vat, String netCost, String grossCost, String totalCost, LocalDateTime callDate,
            CallReceiver callReceiver, Set<Users> callUsers) {
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
        this.callReceiver = callReceiver;
        this.callUsers = callUsers;
    }

    public LocalDateTime getCallDate() {
        return callDate;
    }

    public void setCallDate(LocalDateTime callDate) {
        this.callDate = callDate;
    }

    public Set<Users> getCallUsers() {
        return callUsers;
    }

    public void setCallUsers(Set<Users> callUsers) {
        this.callUsers = callUsers;
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

    public CallDto() {
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
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getDuration() {
        return duration;
    }
 
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public CallReceiver getCallReceiver() {
        return callReceiver;
    }

    public void setCallReceiver(CallReceiver callReceiver) {
        this.callReceiver = callReceiver;
    }

    @Override
    public String toString() {
        return "CallDto [startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration
                + ", costPerMinute=" + costPerMinute + ", discountForCalls=" + discountForCalls + ", vat=" + vat
                + ", netCost=" + netCost + ", grossCost=" + grossCost + ", totalCost=" + totalCost + ", callDate="
                + callDate + ", callReceiver=" + callReceiver + ", callUsers=" + callUsers + "]";
    }

}
