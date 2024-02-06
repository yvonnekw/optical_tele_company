package opticaltelephonecompany.otc.models;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.Table;

@Entity
@Table(name = "call_receiver")
public class CallReceiver {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long callReceiverId;
    private String firstName;
    private String lastName;
	private String telephone;
    private String destinationCountry;
    
    /* 
    @OneToMany(mappedBy = "callReceiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> receivedCalls = new ArrayList<>();*/
    
    /* 
    @OneToMany(mappedBy = "callReceiver", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Call> receivedCalls;
    
    @ManyToOne
    @JoinColumn(name = "call_user_id")
    @JsonBackReference
    private CallUser callUser;*/
    
    @ManyToMany(mappedBy = "callReceivers")
    private Set<CallUser> callUsers;


    public CallReceiver() {
    }
    

    public CallReceiver(Long callReceiverId, String firstName, String lastName, String telephone,
            String destinationCountry, Set<CallUser> callUsers) {
        this.callReceiverId = callReceiverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.destinationCountry = destinationCountry;
        this.callUsers = callUsers;
    }

    public Long getCallReceiverId() {
        return callReceiverId;
    }
    public void setCallReceiverId(Long callReceiverId) {
        this.callReceiverId = callReceiverId;
    }
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
  
    public CallReceiver(Long callReceiverId, String firstName, String lastName, String telephone,
            String destinationCountry) {
        this.callReceiverId = callReceiverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.destinationCountry = destinationCountry;
    }

    public Set<CallUser> getCallUsers() {
        return callUsers;
    }


    public void setCallUsers(Set<CallUser> callUsers) {
        this.callUsers = callUsers;
    }

    @Override
    public String toString() {
        return "CallReceiver [callReceiverId=" + callReceiverId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", telephone=" + telephone + ", destinationCountry=" + destinationCountry + ", callUsers=" + callUsers
                + "]";
    }


    

}
