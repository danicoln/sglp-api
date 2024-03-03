package com.sglp.sglp_api.api.disassembler;

import com.sglp.sglp_api.api.dto.input.ProcessoInput;
import com.sglp.sglp_api.domain.model.Processo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Processo toDomainObject(ProcessoInput input) {
        return modelMapper.map(input, Processo.class);
    }

    public void copyToDomainObject(ProcessoInput input, Processo objeto) {
        modelMapper.map(input, objeto);
    }
}
