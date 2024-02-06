package opticaltelephonecompany.otc.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import opticaltelephonecompany.otc.controllers.CallReceiverController;
import opticaltelephonecompany.otc.exception.UserDoesNotExistException;
import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallDto;
import opticaltelephonecompany.otc.models.CallReceiver;
import opticaltelephonecompany.otc.models.CallReceiverDto;
import opticaltelephonecompany.otc.models.CallUser;
import opticaltelephonecompany.otc.models.RegistrationDto;
import opticaltelephonecompany.otc.repository.CallReceiverRepository;
import opticaltelephonecompany.otc.repository.CallRepository;
import opticaltelephonecompany.otc.repository.UserRepository;

@Service
public class CallReceiverService {

    private final CallRepository callRepository;
    private final UserRepository userRepository;
    private final CallReceiverRepository callReceiverRepository;



    public CallReceiverService(CallRepository callRepository, UserRepository userRepository,
            CallReceiverRepository callReceiverRepository) {
        this.callRepository = callRepository;
        this.userRepository = userRepository;
        this.callReceiverRepository = callReceiverRepository;
    }



    public CallReceiver addCallReceiver(String username, CallReceiverDto callReceiverDTO) {
        CallReceiver callReceiver = new CallReceiver();
        try {
                CallUser user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserDoesNotExistException());
                callReceiver.setFirstName(callReceiverDTO.getFirstName());
                callReceiver.setLastName(callReceiverDTO.getLastName());
                callReceiver.setTelephone(callReceiverDTO.getTelephone());
                callReceiver.setDestinationCountry(callReceiverDTO.getDestinationCountry());
                callReceiver.setDestinationCountry(callReceiverDTO.getDestinationCountry());
                //callReceiver.setCallUser(user);
               // call.setCostPerMinute(callsDTO.getCostPerMinute());
       
                System.out.println("call details " + callReceiver);
        
                return callReceiverRepository.save(callReceiver);
            } catch (Exception e) {
                e.getStackTrace();
            }

            return null;
       
    }


   // public List<CallReceiver> getCallReceiversForUser(String username) {
       // return callRepository.findByCallUser(username);
  //  }

    /* 

    public Call makeCall(CallDto callDTO) {
        Call call = new Call();
        Call saveCall = callRepository.save(call);
        return (saveCall);
    }

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
        CallUser user = userRepository.findById(callId).orElseThrow(
            () -> new ResourceNotFoundException("User not found with the given Id : " + callId)
        );

        callRepository.deleteById(callId);
    }

    public CallService(CallRepository callsRepository, UserRepository userRepository){
        this.callRepository = callsRepository;
        this.userRepository = userRepository;
    }

    public Call makeCall(String username, CallDto callsDTO) throws Exception {
        CallUser user = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
        System.out.println("user name " + username);
        
        Call call = new Call();
            try {
                call.setStartTime(callsDTO.getStartTime());
                call.setEndTime(callsDTO.getEndTime());
                call.setDuration(callsDTO.getDuration());
                call.setCallUser(user);

                System.out.println("call details " + call);
                System.out.println("user details  " + user);
        
        return callRepository.save(call);
        } catch (Exception e) {
            e.getStackTrace();
        }
    
        return null;
    }
    */
    
}
