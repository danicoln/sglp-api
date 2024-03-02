package com.sglp.sglp_api.api.disassembler;

import com.sglp.sglp_api.api.dto.input.ExameDaMateriaInput;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ExameDaMateriaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public ExameDaMateria toDomainObject(ExameDaMateriaInput input) {
        return modelMapper.map(input, ExameDaMateria.class);
    }

    public void copyToDomainObject(ExameDaMateriaInput input, ExameDaMateria objeto) {
        objeto.setObjetos(new ArrayList<>());
        modelMapper.map(input, objeto);
    }
}
