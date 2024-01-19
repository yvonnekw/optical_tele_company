package com.otc.opticaltc.mapper;

import com.otc.opticaltc.dto.CallDto;
import com.otc.opticaltc.models.Call;


public class CallMapper {
    public static CallDto mapToCallDto(Call call){
        return new CallDto(
            call.getCallId(),
            call.getStartTime(),
            call.getEndTime()

        );
    }

    public static Call mapToCall(CallDto callDto){
        return new Call(
            callDto.getCallId(),
            callDto.getStartTime(),
            callDto.getEndTime()
        );
    }
    
}
