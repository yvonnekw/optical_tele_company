package opticaltelephonecompany.otc.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//@Document(indexName ="user1")
@Entity
@Table(name="users")
public class CallUser implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;
	@Column(unique=true)
    private String username;
    private String password;
	private String firstName;
    private String lastName;
	private String emailAddress;
	private String mainTelephone;

	public String getMainTelephone() {
		return mainTelephone;
	}

	public void setMainTelephone(String mainTelephone) {
		this.mainTelephone = mainTelephone;
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

	/*Security related */
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(                                                                                                                     
        name="user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
	private Set<Role> authorities;
	
	// mention
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "user_address_junction", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "address_id") })

	private Address address;

	/* 
	@OneToMany(mappedBy = "callUser", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<Call> calls;*/
	
	@ManyToMany
	@JoinTable(name = "call_user_receiver", joinColumns = @JoinColumn(name = "call_user_id"), inverseJoinColumns = @JoinColumn(name = "call_receiver_id"))
	private Set<CallReceiver> callReceivers;

	private Boolean enabled;

	@Column(nullable = true)
	//hide user verification code in the json response
	@JsonIgnore
	private Long verification;

	public CallUser() {
		//super();
		this.authorities = new HashSet<>();
		//when we first create account user should not be able to use it
		//till user is verified
		this.enabled = true;
	}
	

	public CallUser(Long userId, String username, String password, String firstName, String lastName,
			String emailAddress, String mainTelephone, Set<Role> authorities, Address address,
			Set<CallReceiver> callReceivers, Boolean enabled, Long verification) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.mainTelephone = mainTelephone;
		this.authorities = authorities;
		this.address = address;
		this.callReceivers = callReceivers;
		this.enabled = enabled;
		this.verification = verification;
	}

	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Long getVerification() {
		return verification;
	}

	public void setVerification(Long verification) {
		this.verification = verification;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public CallUser(String username, String password, String emailAddress, String mainTelephone) {
		super();
		//this.userId = userId;
		this.username = username;
		this.password = password;
		//this.authorities = authorities;
		this.emailAddress = emailAddress;
	}

	public CallUser(String username, String password, Set<Role> authorities, String emailAddress, String mainTelephone) {
		super();
		//this.userId = userId;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.emailAddress = emailAddress;
	}

    public CallUser(String username, String password, Set<Role> authoritie) {
		this.username = username;
		this.password = password;
		this.authorities = authoritie;
	}

	public Long getUserId() {
		return this.userId;
	}
	
	public void setId(Long userId) {
		this.userId = userId;
	}
	
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return this.authorities;
	}

	@Override
	public String getPassword() {

		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	/* If you want account locking capabilities create variables and ways to set them for the methods below */
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Set<CallReceiver> getCallReceivers() {
		return callReceivers;
	}

	public void setCallReceivers(Set<CallReceiver> callReceivers) {
		this.callReceivers = callReceivers;
	}

	@Override
	public String toString() {
		return "CallUser [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", mainTelephone="
				+ mainTelephone + ", authorities=" + authorities + ", address=" + address + ", callReceivers="
				+ callReceivers + ", enabled=" + enabled + ", verification=" + verification + "]";
	}

	
	
    
}
