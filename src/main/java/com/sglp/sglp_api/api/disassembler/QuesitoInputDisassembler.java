package com.sglp.sglp_api.api.disassembler;

import com.sglp.sglp_api.api.dto.input.QuesitoInput;
import com.sglp.sglp_api.domain.model.Quesito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuesitoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Quesito toDomainObject(QuesitoInput input) {
        return modelMapper.map(input, Quesito.class);
    }

    public void copyToDomainObject(QuesitoInput input, Quesito quesito) {
        modelMapper.map(input, quesito);
    }
}
