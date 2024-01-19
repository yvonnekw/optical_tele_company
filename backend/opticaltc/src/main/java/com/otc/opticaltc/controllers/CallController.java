package com.otc.opticaltc.controllers;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.otc.opticaltc.dto.CallDto;
import com.otc.opticaltc.models.Call;
//import com.otc.opticaltc.models.Call;
//import com.otc.opticaltc.models.CallDTO;
//import com.otc.opticaltc.repositories.CallRepository;
import com.otc.opticaltc.services.CallService;

import lombok.AllArgsConstructor;

//import com.otc.opticaltc.services.CallService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;






@AllArgsConstructor
@RestController
@RequestMapping("/api/calls")
public class CallController {
    //@Autowired

    private CallService callService;

    //build add calls
    @PostMapping//@RequestBody converts the json into a CallDto java object
    public ResponseEntity<CallDto> makeCall(@RequestBody CallDto callDto){
        CallDto savedCall = callService.makeCall(callDto);
        return new ResponseEntity<>(savedCall, HttpStatus.CREATED);
    }
    
    @GetMapping("{id}")//url method argument is band with the Path variable if to the callId
    public ResponseEntity<CallDto> getCallById(@PathVariable("id") long callId){
       CallDto callDto = callService.getCallById(callId);
       return ResponseEntity.ok(callDto);
    }

    @GetMapping
    public ResponseEntity<List<CallDto>> getAllCalls(){
        List<CallDto> calls = callService.getAllCalls();
        return ResponseEntity.ok(calls);
    }

    @PutMapping("{id}")
    public ResponseEntity<CallDto> updateCall(@PathVariable("id") Long callId, @RequestBody CallDto updatedCall){
       CallDto callDto = callService.updateCall(callId, updatedCall);
       return ResponseEntity.ok(callDto);
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
    /* 
    @GetMapping("getcalls")
    public List<Call> getCalls(){
       return callService.getAllCalls();
    }

    @PostMapping("addcall")
    public Call saveCall(@RequestBody Call call) {
        return callService.saveCall(call);
    }*/
    
    

}
