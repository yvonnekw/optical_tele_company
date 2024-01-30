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
import org.springframework.web.bind.annotation.RestController;

import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallDto;
import opticaltelephonecompany.otc.models.CallerUser;
import opticaltelephonecompany.otc.services.CallService;
import opticaltelephonecompany.otc.services.UserService;

@RestController
@RequestMapping("/calls")
@CrossOrigin("*")
public class CallController {
    
    private UserService userService;
    private CallService callService;

    public CallController(CallService callService){
        this.callService = callService;
    }

       //build add calls
    @PostMapping//@RequestBody converts the json into a CallDto java object
    public ResponseEntity<Call> makeCall(@RequestBody CallDto callDto){
        Call savedCall = callService.makeCall(callDto);
        return new ResponseEntity<>(savedCall, HttpStatus.CREATED);
    }
    
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
    }

    @GetMapping("mycalls")
    public String calls(){
        return "my callls";
    }

    @PostMapping("/make/call")
    public Call callsController(@RequestBody LinkedHashMap<String, String> body) throws Exception{
        String userName = body.get("username");
        String startTime = body.get("startTime");
        String endTime = body.get("endTime");
        String duration = body.get("duration");

        CallerUser applicationUser = userService.getUserByUsername(userName);

        CallDto callsDTO = new CallDto();

        callsDTO.setStartTime(Long.parseLong(startTime));
        callsDTO.setEndTime(Long.parseLong(endTime));
        callsDTO.setDuration(Long.parseLong(duration));
    
       Call call =   callService.makeCall(duration, callsDTO);

       // applicationUser.setMainTelephone(Long.parseLong(phone));

       // return callService.makeCall(applicationUser);

        //return userService.updateUser(applicationUser);
       // return "the calls";

       return call;
    }

}
