package com.otc.opticaltc.models;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    //private String duration;
    //private String totalTime;
    //private String costPerMinute;
   // private String discountForCalls;
   // private String signUpDiscount;
    //private String vat;
    //private float netCost;
   // private float grossCost;
    //private float totalCost;

   

}
