package opticaltelephonecompany.otc.controllers;



import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallDto;
import opticaltelephonecompany.otc.models.CallReceiver;
import opticaltelephonecompany.otc.models.CallReceiverDto;
import opticaltelephonecompany.otc.models.Users;
import opticaltelephonecompany.otc.services.CallReceiverService;
import opticaltelephonecompany.otc.services.CallService;
import opticaltelephonecompany.otc.services.UserService;

@RestController
@RequestMapping("/callreceiver")
@CrossOrigin("*")
public class CallReceiverController {
    
   
    private CallReceiverService callReceiverService;

    public CallReceiverController(CallReceiverService callReceiverService){
        this.callReceiverService = callReceiverService;
    }
/* 
       //build add calls
    @PostMapping//@RequestBody converts the json into a CallDto java object
    public ResponseEntity<Call> makeCall(@RequestBody CallDto callDto){
        System.out.println("make call data " + callDto.toString());
        Call savedCall = callService.makeCall(callDto);
        return new ResponseEntity<>(savedCall, HttpStatus.CREATED);
    };*/
    /* 
    @GetMapping("{id}")//url method argument is band with the Path variable if to the callId
    public ResponseEntity<Call> getCallById(@PathVariable("id") long callId){
       Call call = callService.getCallById(callId);
       return ResponseEntity.ok(call);
    }

    @GetMapping
    public ResponseEntity<List<Call>> getAllCalls(){
        List<Call> calls = callService.getAllCalls();
        return ResponseEntity.ok(calls);
    }

    @PutMapping("{id}")
    public ResponseEntity<Call> updateCall(@PathVariable("id") Long callId, @RequestBody Call updatedCall){
       Call call = callService.updateCall(callId, updatedCall);
       return ResponseEntity.ok(call);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCall(@PathVariable("id") Long callId){
       callService.deleteCall(callId);
       return ResponseEntity.ok("Call deleted successfully.");
    }*/

    @GetMapping("/callreceiver")
    public String calls(){
        return "call receiver";
    }

    @PostMapping("/add/reciever")
    public CallReceiver callReceiver(@RequestBody LinkedHashMap<String, String> body) throws Exception {
        //  String firstName = body.get("firstName");
        // String lastName = body.get("lastName");
        String telephone = body.get("telephone");
        // String destinationCountry = body.get("destinationCountry");
        String username = body.get("username");

        //CallUser callUser = userService.getUserByUsername(userName);

        CallReceiverDto callReceiverDTO = new CallReceiverDto();

        // callReceiverDTO.setFirstName(firstName);
        // callReceiverDTO.setLastName(lastName);
        callReceiverDTO.setTelephone(telephone);
        // callReceiverDTO.setDestinationCountry(destinationCountry);

        CallReceiver callReceiver = callReceiverService.addCallReceiver(username, callReceiverDTO);

        return callReceiver;
    }

    @GetMapping("/phone-numbers")
    public ResponseEntity<List<String>> findDistinctTelephoneByUserUsername(@RequestParam String username) {
        System.out.println("username " + username);
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<String> phoneNumbers = callReceiverService.findDistinctTelephoneByUserUsername(username);

        System.out.println("phone numbers " + phoneNumbers);
        return ResponseEntity.ok(phoneNumbers);
    }

    @GetMapping("/phone-exists")
    public ResponseEntity<Boolean> checkPhoneNumberExists(
            @RequestParam String username,
            @RequestParam String telephone) {

        // Check if either username or phoneNumber is missing
        if (username == null || username.isEmpty() || telephone == null || telephone.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Perform the check for phone number existence
        boolean exists = callReceiverService.checkPhoneNumberExistsForUser(username, telephone);
        System.out.println("yes or no  " + exists);
        return ResponseEntity.ok(exists);
    }
    
    /* 
    @GetMapping("/tele")
    public ResponseEntity<List<String>> getDistinctPhoneNumbersForUser(@RequestBody LinkedHashMap<String, String> body) {
        //List<String> phoneNumbers = callReceiverService.getDistinctPhoneNumbersForUser(username);
        // return ResponseEntity.ok(phoneNumbers);
        //String username = requestBody.get("username");
        String username = body.get("username");
        if (username == null) {
            return ResponseEntity.badRequest().build();
        }

        List<String> phoneNumbers = callReceiverService.getDistinctPhoneNumbersForUser(username);
        return ResponseEntity.ok(phoneNumbers);
    }

/* 
    @GetMapping("/phone-numbers/{username}")
    public ResponseEntity<List<String>> getDistinctPhoneNumbersForUser(@PathVariable String username) {
        List<String> phoneNumbers = callReceiverService.getDistinctPhoneNumbersForUser(username);
        return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
    }*/
   
     

}
