package com.sglp.sglp_api.api.disassembler;

import com.sglp.sglp_api.api.dto.input.PeritoInput;
import com.sglp.sglp_api.domain.model.Perito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeritoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Perito toDomainObject(PeritoInput input) {
        return modelMapper.map(input, Perito.class);
    }

    public void copyToDomainObject(PeritoInput input, Perito perito) {
        modelMapper.map(input, perito);
    }
}
