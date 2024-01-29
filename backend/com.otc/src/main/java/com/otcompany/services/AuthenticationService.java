package com.otcompany.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otcompany.models.Address;
import com.otcompany.models.CallerUser;
import com.otcompany.models.LoginResponseDto;
import com.otcompany.models.Role;
import com.otcompany.models.Telephone;
import com.otcompany.repository.RoleRepository;
import com.otcompany.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public CallerUser registerUser(String username, String password, String emailAddress, String telephone){

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
       // Set<Telephone> telephone = new HashSet<>();
        //Address address = new Address();

        authorities.add(userRole);
         //return userRepository.save(new ApplicationUser(username, encodedPassword, authorities, firstName));

        return userRepository.save(new CallerUser(username, encodedPassword, authorities, emailAddress, telephone));
    }

    public LoginResponseDto loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDto(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDto(null, "");
        }
    }

}
