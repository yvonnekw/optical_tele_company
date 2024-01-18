package com.otc.opticaltc.controllers;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.otc.opticaltc.models.Call;
//import com.otc.opticaltc.models.Call;
//import com.otc.opticaltc.models.CallDTO;
//import com.otc.opticaltc.repositories.CallRepository;
import com.otc.opticaltc.services.CallService;

//import com.otc.opticaltc.services.CallService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class CallController {


    @Autowired
    CallService callService;

    //@GetMapping("/calls")
    //public String showCalls(Model model) {
       // List<Call> calls = callService.getAllCalls();
       // model.addAttribute("calls", calls);
       //return "calls";
    //}

    @GetMapping("calls")
    public String calls(){
        return "my callls";
    }
    
    @GetMapping("getcalls")
    public List<Call> getCalls(){
       return callService.getAllCalls();
    }

    @PostMapping("addcall")
    public Call saveCall(@RequestBody Call call) {
        return callService.saveCall(call);
    }
    
    

}
