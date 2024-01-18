package com.otc.opticaltc.models;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "calls")
public class Call {

    @Id
    @Column(name ="call_id")
   @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long callId;
    @Column(name="start_time")
    private String startTime;
    @Column(name="end_time")
    private String endTime;
    //private long duration;
    //private long totalTime;
    //private float costPerMinute;
   // private String discountForCalls;
   // private String signUpDiscount;
    //private String vat;
    //private float netCost;
   // private float grossCost;
    //private float totalCost;

    public long getCallId() {
        return callId;
    }
    public void setCallId(long callId) {
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
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "Call [callId=" + callId + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }
   
  
/* 
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public float getCostPerMinute() {
        return costPerMinute;
    }

    public void setCostPerMinute(float costPerMinute) {
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

    public float getNetCost() {
        return netCost;
    }

    public void setNetCost(float netCost) {
        this.netCost = netCost;
    }

    public float getGrossCost() {
        return grossCost;
    }

    public void setGrossCost(float grossCost) {
        this.grossCost = grossCost;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Call [callId=" + callId + ", startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration
                + ", totalTime=" + totalTime + ", costPerMinute=" + costPerMinute + ", discountForCalls="
                + discountForCalls + ", signUpDiscount=" + signUpDiscount + ", vat=" + vat + ", netCost=" + netCost
                + ", grossCost=" + grossCost + ", totalCost=" + totalCost + "]";
    }

    */

   







}
