package com.otc.opticaltc.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otc.opticaltc.models.Call;
import com.otc.opticaltc.repositories.CallRepository;

//import com.otcompany.exception.UserDoesNotExistException;
//import com.otcompany.models.ApplicationUser;
//import com.otcompany.models.UserCall;
//import com.otcompany.models.UserCallDTO;
////import com.otcompany.models.RegistrationDTO;

//import com.otcompany.repository.UserRepository;

import java.util.List;

@Service
public class CallService {
   @Autowired
   private CallRepository callRepository;

   public CallService(){

   }

    public List<Call> getAllCalls() {
       return callRepository.findAll();
    }

    public Call saveCall(Call city) {
        return callRepository.save(city);
    }

}
