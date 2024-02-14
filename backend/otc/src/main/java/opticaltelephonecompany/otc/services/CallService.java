package opticaltelephonecompany.otc.services;



import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import opticaltelephonecompany.otc.controllers.CallReceiverController;
import opticaltelephonecompany.otc.exception.CallCreationException;
import opticaltelephonecompany.otc.exception.CallReceiverNotFoundException;
import opticaltelephonecompany.otc.exception.UserDoesNotExistException;
import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallDto;
import opticaltelephonecompany.otc.models.CallReceiver;
import opticaltelephonecompany.otc.models.CallReceiverDto;
import opticaltelephonecompany.otc.models.Users;
import opticaltelephonecompany.otc.models.RegistrationDto;
import opticaltelephonecompany.otc.models.Role;
import opticaltelephonecompany.otc.repository.CallReceiverRepository;
import opticaltelephonecompany.otc.repository.CallRepository;
import opticaltelephonecompany.otc.repository.UserRepository;

@Service
public class CallService {

    private final CallRepository callRepository;
    private final UserRepository userRepository;
    private final CallReceiverRepository callReceiverRepository;

 /* 
    public Call makeCall(String username, CallDto callDTO) {
        CallUser user = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
        System.out.println("user name " + username);
        Call call = new Call();
        Call saveCall = callRepository.save(call);
        return (saveCall);
    }*/

    public Call getCallById(Long callId) {
        return callRepository.findById(callId).orElseThrow(UserDoesNotExistException::new);
    }

    public List<Call> getAllCalls() {
        return callRepository.findAll();
    }

    public Call updateCall(Long callId, Call updatedCall){

        Call call = callRepository.findById(callId).orElseThrow(
            () -> new ResourceNotFoundException("Call not found with the given Id : " + callId)
        );
        call.setStartTime(updatedCall.getStartTime());
        call.setEndTime(updatedCall.getEndTime());

        Call updatedCallObj = callRepository.save(call);
       return updatedCallObj;
    }

    public void deleteCall(Long callId) {
        Users user = userRepository.findById(callId).orElseThrow(
            () -> new ResourceNotFoundException("User not found with the given Id : " + callId)
        );

        callRepository.deleteById(callId);
    }

    public CallService(CallRepository callsRepository, UserRepository userRepository, CallReceiverRepository callReceiverRepository){
        this.callRepository = callsRepository;
        this.userRepository = userRepository;
        this.callReceiverRepository = callReceiverRepository;
    }

    
    public Call makeCall(String username, String telephone, CallDto callsDTO) throws Exception {

        try {
            Users user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserDoesNotExistException());

            CallReceiver callReceiver = callReceiverRepository.findByTelephone(telephone)
                    .orElseThrow(() -> new CallReceiverNotFoundException());

            Call call = new Call();
            call.setStartTime(callsDTO.getStartTime());
            call.setEndTime(callsDTO.getEndTime());
            call.setDuration(callsDTO.getDuration());
            call.setTotalTime(callsDTO.getTotalTime());
            call.setCostPerMinute(callsDTO.getCostPerMinute());
            call.setDiscountForCalls(callsDTO.getDiscountForCalls());
            call.setSignUpDiscount(callsDTO.getSignUpDiscount());
            call.setVat(callsDTO.getVat());
            call.setNetCost(callsDTO.getNetCost());
            call.setGrossCost(callsDTO.getGrossCost());
            call.setTotalCost(callsDTO.getTotalCost());
            call.setUser(user);
            call.setReceiver(callReceiver);

            // Uncomment the following line if you want to add CallUsers to the set
            // callUsers.add(UserRepository.findByCallUser(callUsers).get());

            System.out.println("Call details: " + call);
            System.out.println("User details: " + user);

            return callRepository.save(call);
        } catch (UserDoesNotExistException | CallReceiverNotFoundException e) {
            throw e; // Rethrow the exception to be handled at a higher level
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception details
            throw new CallCreationException();
        }
        /* 
        
        CallUser user = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
        System.out.println("user name " + username);
        CallReceiver callReceiver = callReceiverRepository.findByTelephone(
                telephone).orElseThrow(UserDoesNotExistException::new);
        System.out.println("call receiver telephone " + telephone);
        
        Call call = new Call();
            try {
                call.setStartTime(callsDTO.getStartTime());
                call.setEndTime(callsDTO.getEndTime());
                call.setDuration(callsDTO.getDuration());
                call.setTotalTime(callsDTO.getTotalTime());
                call.setCostPerMinute(callsDTO.getCostPerMinute());
                call.setDiscountForCalls(callsDTO.getDiscountForCalls());
                call.setSignUpDiscount(callsDTO.getSignUpDiscount());
                call.setVat(callsDTO.getVat());
                call.setNetCost(callsDTO.getNetCost());
                call.setGrossCost(callsDTO.getGrossCost());
                call.setTotalCost(callsDTO.getTotalCost());
                call.setCallUser(user);
                call.setCallReceiver(callReceiver);
        
        
                 Set<CallUser> callUsers = callsDTO.getCallUsers();
               // callUsers.add(UserRepository.findByCallUser(callUsers).get());
                callsDTO.setCallUsers(callUsers);
        
                System.out.println("call details " + call);
                System.out.println("user details  " + user);
        
                return callRepository.save(call);
        // } catch (Exception e) {
          //  e.getStackTrace();
        // }
            } catch (UserDoesNotExistException | CallReceiverNotFoundException e) {
             throw e; // Rethrow the exception to be handled at a higher level
         } catch (Exception e) {
             e.printStackTrace(); // Log the exception details
             throw new CallCreationException();
         }
            
         */

        // return null;
    }

    /* 
    public List<Call> getCallsForReceiver(String username) {
        return callRepository.findByReceiverUsername(username);
    }*/
    /* 
    public List<CallReceiver> getCallReceiversForUser(String username) {
        return callReceiverRepository.findByUsername(username);
    }*/
/* 
    public List<Call> getAllCallsForUser(CallUser callUser) {
        return callRepository.findByCallUser(callUser);
    }*/

    /* 
    public List<Call> getCallReceiversForUser(String username) {
         return callRepository.findAllCallReceiverByUserName(username);
        //return null;
    }*/

    /* 
public List<Call> getCallReceiversForUser(String username) {
    return callRepository.findByCallUserUsername(username);
}*/
  /*  
public List<CallReceiver> getCallReceiversForUser(String username) {
    return callRepository.findByCallUsersUsername(username);
}*/
/* 
    public List<CallReceiver> getCallReceiversForUser(String username) {
        // Assuming you have a method in your repository to fetch call receivers by
        // username
        CallUser callUser = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
        return callReceiverRepository.findByCallUser(callUser);//.findByCallUser(username);
    }*/
    
    
}
