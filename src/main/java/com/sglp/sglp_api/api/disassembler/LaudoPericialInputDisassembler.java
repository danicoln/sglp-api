package com.sglp.sglp_api.api.disassembler;

import com.sglp.sglp_api.api.dto.input.LaudoPericialInput;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LaudoPericialInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public LaudoPericial toDomainObject(LaudoPericialInput input) {
        return modelMapper.map(input, LaudoPericial.class);
    }

    public void copyToDomainObject(LaudoPericialInput input, LaudoPericial laudoPericial) {

        laudoPericial.setObjetos(new ArrayList<>());
        laudoPericial.setExameDaMateria(new ExameDaMateria());
        laudoPericial.setQuesitos(new ArrayList<>());

        modelMapper.map(input, laudoPericial);
    }
}
