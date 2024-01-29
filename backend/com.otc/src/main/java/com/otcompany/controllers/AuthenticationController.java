package com.otcompany.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otcompany.exception.EmailAlreadyTakenException;
import com.otcompany.exception.UserDoesNotExistException;
import com.otcompany.models.CallerUser;
import com.otcompany.models.LoginResponseDto;
import com.otcompany.models.RegistrationDto;
import com.otcompany.services.AuthenticationService;
import com.otcompany.services.TokenService;
import com.otcompany.services.UserService;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserService userService, TokenService tokenService, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken(){
        return new ResponseEntity<String>("The email you provided isa already taken", HttpStatus.CONFLICT);

    }
     
    /* 
    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDto body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getEmailAddress(), body.getMainTelephone());
    }*/
    

    @PostMapping("/register")
    public CallerUser registerUser(@RequestBody RegistrationDto body){
        return userService.registerUser(body);
    }

    
    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody LinkedHashMap<String, String> body){
        String username = body.get("username");
        String password = body.get("password");

        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

                String token = tokenService.generateJwt(auth);
                return new LoginResponseDto(userService.getUserByUsername(username), token);

         } catch(RuntimeException e) {  //(AuthenticationException e) {
            return new LoginResponseDto(null, "");
        }

        }
        //return authenticationService.loginUser(body.getUsername(), body.getPassword());

/* 
    @PostMapping("/login")
    public ApplicationUser login(@RequestBody LinkedHashMap<String, String> body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }*/

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<String> handleUserDoesNotExist(){
        return new ResponseEntity<String>("The user you are looking for does not exist.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/telephone")
    public CallerUser updateTelephoneNumber(@RequestBody LinkedHashMap<String, String> body){

        String userName = body.get("username");
        String phone = body.get("mainTelephone");
    
        CallerUser applicationUser = userService.getUserByUsername(userName);

        applicationUser.setMainTelephone((phone));

        return userService.updateUser(applicationUser);
    }

    @PostMapping("/email/code")
    public ResponseEntity<String> createEmailVerification(@RequestBody LinkedHashMap<String, String> body) {

        userService.generateEmailVerification(body.get("username"));

        return new ResponseEntity<String>("Verification code generated, email sent.", HttpStatus.OK);

    }

    @PutMapping("/update/password")
    public CallerUser updatePassword(@RequestBody LinkedHashMap<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

    return userService.setPassword(username, password);

    }

}
