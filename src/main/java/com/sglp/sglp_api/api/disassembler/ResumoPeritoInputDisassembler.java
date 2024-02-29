package com.sglp.sglp_api.api.disassembler;

import com.sglp.sglp_api.api.dto.input.ResumoPeritoInput;
import com.sglp.sglp_api.domain.model.ResumoPerito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResumoPeritoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public ResumoPerito toDomainObject(ResumoPeritoInput input) {
        return modelMapper.map(input, ResumoPerito.class);
    }

    public void copyToDomainObject(ResumoPeritoInput input, ResumoPerito resumoPerito) {
        modelMapper.map(input, resumoPerito);
    }
}
