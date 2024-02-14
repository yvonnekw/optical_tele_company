package opticaltelephonecompany.otc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallReceiver;
import opticaltelephonecompany.otc.models.Users;
import opticaltelephonecompany.otc.publisher.RabbitMQJsonProducer;
import opticaltelephonecompany.otc.publisher.RabbitMQProducer;
import opticaltelephonecompany.otc.models.RegistrationDto;
import opticaltelephonecompany.otc.services.AuthenticationService;
import opticaltelephonecompany.otc.services.CallReceiverService;
import opticaltelephonecompany.otc.services.CallService;
import opticaltelephonecompany.otc.services.ImageService;
import opticaltelephonecompany.otc.services.TokenService;
import opticaltelephonecompany.otc.services.UserService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {


    private final RabbitMQProducer rabbitMQProducer;
    private final UserService userService;
    private final TokenService tokenService;
    private final CallService callService;
    private final CallReceiverService callReceiverService;

    private RabbitMQJsonProducer rabbitMQJsonProducer;

    @Autowired
    public UserController(UserService userService, TokenService tokenService, CallService callService, 
            CallReceiverService callReceiverService, RabbitMQProducer rabbitMQProducer, 
            RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.callService = callService;
        this.callReceiverService = callReceiverService;
        this.rabbitMQProducer = rabbitMQProducer;
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }
    
    @PostMapping("/mess-publisher")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Users user) {
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json message sent RabbitMQ...");
    }

    @GetMapping("/verify")
    public Users verifyIdentity(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = "";
        Users user;

        if(token.substring(0,6).equals("Bearer")) {
            String strippedToken = token.substring(7);
            username = tokenService.getUsernameFromToken(strippedToken);
        }
        try {
            user = userService.getUserByUsername(username);
        } catch(Exception e) {
            user = null;
        }
        
        return user;
    }
    

    @GetMapping("/hello")
    public ResponseEntity<String> helloUserController(@RequestParam("message") String message) {
        rabbitMQProducer.sendMessage(message);
        // return "User access level";
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }

    @GetMapping("/")
    public ResponseEntity<String> helloUserController2() {
        rabbitMQProducer.sendMessage("User access level new message");
        // return "User access level";
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }

    @GetMapping("{id}")//url method argument is band with the Path variable if to the callId
    public ResponseEntity<Users> getUserById(@PathVariable("id") long userId){
        Users userDto = userService.getUserById(userId);
       return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") Long userId, @RequestBody Users updatedUser){
       Users userDto = userService.updateUser(userId, updatedUser);
       return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCall(@PathVariable("id") Long userId){
       userService.deleteUser(userId);
       return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("users")
    public String users() {
        return "my users";
    }
/* 
    @GetMapping("/{username}/phonenumbers")
    public ResponseEntity<List<String>> getPhoneNumbersForUser(@PathVariable String username) {
        List<String> phoneNumbers = callReceiverService.getPhoneNumbersForUser(username);
        return ResponseEntity.ok(phoneNumbers);
    }*/

    
}
