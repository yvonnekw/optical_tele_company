package opticaltelephonecompany.otc.models;


import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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


    public CallReceiver(String firstName, String lastName, String telephone, String destinationCountry, Set<Call> calls) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.destinationCountry = destinationCountry;
        this.calls = calls;
    }

    public CallReceiver(Long callReceiverId, String firstName, String lastName, String telephone,
            String destinationCountry) {
        this.callReceiverId = callReceiverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.destinationCountry = destinationCountry;
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
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(                                                                                                                     
        name="call_call_receiver_junction",
        joinColumns = {@JoinColumn(name="call_id")},
        inverseJoinColumns = {@JoinColumn(name="call_receiver_id")}
    )
    private Set<Call> calls;


}
