package opticaltelephonecompany.otc.services;



import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import opticaltelephonecompany.otc.controller.CallReceiverController;
import opticaltelephonecompany.otc.dto.CallDto;
import opticaltelephonecompany.otc.dto.CallReceiverDto;
import opticaltelephonecompany.otc.dto.CompletedCallDto;
import opticaltelephonecompany.otc.dto.InvoiceWithCallIdsDTO;
import opticaltelephonecompany.otc.dto.RegistrationDto;
import opticaltelephonecompany.otc.exception.CallCreationException;
import opticaltelephonecompany.otc.exception.CallReceiverNotFoundException;
import opticaltelephonecompany.otc.exception.UserDoesNotExistException;
import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallReceiver;
import opticaltelephonecompany.otc.models.CurrentCall;
import opticaltelephonecompany.otc.models.Users;
import opticaltelephonecompany.otc.models.Role;
import opticaltelephonecompany.otc.repository.CallReceiverRepository;
import opticaltelephonecompany.otc.repository.CallRepository;
import opticaltelephonecompany.otc.repository.UserRepository;


public interface CallService {

    public Call getCallById(Long callId);

    public List<Call> getAllCalls();
    

    public Call updateCall(Long callId, Call updatedCall);

    public void deleteCall(Long callId);


    public Call makeCall(String username, String telephone, CallDto callsDTO);

    public List<Call> getCallsByUsername(String username);

    public List<Call> getUnpaidCallsByUsername(String username);

    void endCallsAndGenerateInvoice(List<Call> calls);

    CompletedCallDto convertToCompletedCallDto(CurrentCall currentCall);
   // public void endCallsAndGenerateInvoice(List<Call> calls);
  // void endCallsAndGenerateInvoice(List<Call> calls, String username, InvoiceWithCallIdsDTO invoiceWithCallIdsDTO);

}
