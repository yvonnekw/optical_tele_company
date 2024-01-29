package com.otcompany.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.otcompany.exception.EmailAlreadyTakenException;
import com.otcompany.exception.UserDoesNotExistException;
import com.otcompany.models.CallerUser;
import com.otcompany.models.RegistrationDto;
import com.otcompany.models.Role;
import com.otcompany.repository.RoleRepository;
import com.otcompany.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

   // @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //@Autowired
    public CallerUser registerUser(RegistrationDto registrationDTO) {
         
        CallerUser applicationUser = new CallerUser();
    
        applicationUser.setFirstName(registrationDTO.getFirstName());
        applicationUser.setLastName(registrationDTO.getLastName());
        applicationUser.setEmailAddress(registrationDTO.getEmailAdress());
       // applicationUser.setUsername(registrationDTO.getUsername());
        applicationUser.setPassword(registrationDTO.getPassword());

      // ApplicationUser encryptPassword = setPassword(registrationDTO.getLastName(), registrationDTO.getPassword());

       // applicationUser.setPassword(encryptPassword.toString());


        String name = applicationUser.getFirstName() + applicationUser.getLastName();
        boolean nameTaken = true;
        String tempName = " ";

        while(nameTaken) {
            tempName = generateUserName(name);
            
            if(userRepository.findByUsername(tempName).isEmpty()){
                nameTaken = false;
            }
        }
        applicationUser.setUsername(tempName);

        Set<Role> roles = registrationDTO.getAuthorities();
        roles.add(roleRepository.findByAuthority("USER").get());
        registrationDTO.setAuthorities(roles);

        //Set<Role> roles =  (Set<Role>) applicationUser.getAuthorities();
       // roles.add(roleRepository.findByAuthority("USER").get());
        //applicationUser.setAuthorities(roles);

        try {
            return userRepository.save(applicationUser);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }
    }

    private String generateUserName(String name) {
        long generatedNumber = (long) Math.floor(Math.random() * 1_000_000_000);
        return name + generatedNumber;
    }

    public void generateEmailVerification(String username) {
        CallerUser applicationUser = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
        applicationUser.setVerification(generatedVerificationNumber());
        userRepository.save(applicationUser);
    }

   public CallerUser setPassword(String username, String password) {
        CallerUser applicationUser = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
      String encodedPassword = passwordEncoder.encode(password);
      applicationUser.setPassword(encodedPassword);
         return userRepository.save(applicationUser);
    }

   private Long generatedVerificationNumber() {
         long generatedNumber = (long)Math.floor(Math.random() * 100_000_000);
         return  generatedNumber;
    }

    public CallerUser getUserByUsername(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(UserDoesNotExistException::new);
    }

    public CallerUser updateUser(CallerUser applicationUser) {
        try {
            return userRepository.save(applicationUser);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }
    }

    public CallerUser getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(UserDoesNotExistException::new);
    }

    public List<CallerUser> getAllUsers() {
        return userRepository.findAll();
    }

    public CallerUser updateUser(Long userId, CallerUser updatedUser){

        CallerUser user = userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("Call not found with the given Id : " + userId)
        );
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());

        CallerUser updatedUserObj = userRepository.save(user);
       return updatedUserObj;

    }

    public void deleteUser(Long userId) {
        CallerUser user = userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("User not found with the given Id : " + userId)
        );

        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       CallerUser user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found "));

        Set<GrantedAuthority> authorities = user.getAuthorities()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
            .collect(Collectors.toSet());

            UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);

        return userDetails;
    }


}
