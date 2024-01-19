package com.otc.opticaltc.services.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otc.opticaltc.dto.CallDto;
import com.otc.opticaltc.exceptions.ResourceNotFoundException;
import com.otc.opticaltc.mapper.CallMapper;
import com.otc.opticaltc.models.Call;
import com.otc.opticaltc.repositories.CallRepository;
import com.otc.opticaltc.services.CallService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CallServiceImpl implements CallService {

    //@Autowired
    private CallRepository callRepository;
    @Override
    public CallDto makeCall(CallDto callDto) {
        Call call = CallMapper.mapToCall(callDto);
        Call saveCall = callRepository.save(call);
        return CallMapper.mapToCallDto(saveCall);
        
    }
    @Override
    public CallDto getCallById(Long callId) {
      
       Call call =  callRepository.findById(callId)
            .orElseThrow(() -> new ResourceNotFoundException("Call not found with the given Id : " + callId));
            return CallMapper.mapToCallDto(call);
    }
    
    @Override
    public List<CallDto> getAllCalls() {
       List<Call> calls = callRepository.findAll();
        return calls.stream().map((call) -> CallMapper.mapToCallDto(call))
            .collect(Collectors.toList());
    }

    @Override
    public CallDto updateCall(Long callId, CallDto updatedCall) {
        Call call = callRepository.findById(callId).orElseThrow(
            () -> new ResourceNotFoundException("Call not found with the given Id : " + callId)
        );
        call.setStartTime(updatedCall.getStartTime());
        call.setEndTime(updatedCall.getEndTime());

        Call updatedCallObj = callRepository.save(call);
       return CallMapper.mapToCallDto(updatedCallObj);
    }

    @Override
    public void deleteCall(Long callId) {
        Call call = callRepository.findById(callId).orElseThrow(
            () -> new ResourceNotFoundException("Call not found with the given Id : " + callId)
        );

        callRepository.deleteById(callId);
    }
    

}
