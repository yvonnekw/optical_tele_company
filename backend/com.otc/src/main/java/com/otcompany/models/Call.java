package com.otcompany.models;


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
@Table(name = "call")
public class Call {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long callId;
    private String firstName;
    private String lastName;
	private String telephone;
	private String destinationCountry;
    private String startTime;
    private String endTime;
    private String duration;
    private String totalTime;
    private String costPerMinute;
    private String discountForCalls;
    private String signUpDiscount;
    private String vat;
    private float netCost;
    private float grossCost;
    private float totalCost;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public CallerUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(CallerUser applicationUser) {
        this.applicationUser = applicationUser;
    }


   
    //mention
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private CallerUser applicationUser;

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
        return "Call [callId=" + callId + ", firstName=" + firstName + ", lastName=" + lastName + ", telephone="
                + telephone + ", destinationCountry=" + destinationCountry + ", startTime=" + startTime + ", endTime="
                + endTime + ", duration=" + duration + ", totalTime=" + totalTime + ", costPerMinute=" + costPerMinute
                + ", discountForCalls=" + discountForCalls + ", signUpDiscount=" + signUpDiscount + ", vat=" + vat
                + ", netCost=" + netCost + ", grossCost=" + grossCost + ", totalCost=" + totalCost
                + ", applicationUser=" + applicationUser + "]";
    }

    

   







}
