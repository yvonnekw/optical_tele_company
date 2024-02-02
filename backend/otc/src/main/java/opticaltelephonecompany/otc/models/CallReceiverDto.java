package opticaltelephonecompany.otc.models;

public class CallReceiverDto {

    private Long callReceiverId;
    private String firstName;
    private String lastName;
	private String telephone;
	private String destinationCountry;

    
    public CallReceiverDto(String firstName, String lastName, String telephone, String destinationCountry) {
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
    @Override
    public String toString() {
        return "CallReceiverDto [callReceiverId=" + callReceiverId + ", firstName=" + firstName + ", lastName="
                + lastName + ", telephone=" + telephone + ", destinationCountry=" + destinationCountry + "]";
    }
    

   
}
