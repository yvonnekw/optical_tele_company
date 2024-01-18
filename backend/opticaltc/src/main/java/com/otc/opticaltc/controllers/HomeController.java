package com.otc.opticaltc.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {


    @GetMapping("sayhello")
    public String sayHello(){
        return "Hello user";
    }
    
}
