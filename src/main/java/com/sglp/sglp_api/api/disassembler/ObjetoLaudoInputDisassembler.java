package com.sglp.sglp_api.api.disassembler;

import com.sglp.sglp_api.api.dto.input.ObjetoLaudoInput;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ObjetoLaudoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public ObjetoLaudo toDomainObject(ObjetoLaudoInput input) {
        return modelMapper.map(input, ObjetoLaudo.class);
    }

    public void copyToDomainObject(ObjetoLaudoInput input, ObjetoLaudo objeto) {
        modelMapper.map(input, objeto);
    }
}
