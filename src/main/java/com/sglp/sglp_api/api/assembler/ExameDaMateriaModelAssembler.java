package com.sglp.sglp_api.api.assembler;

import com.sglp.sglp_api.api.dto.model.ExameDaMateriaModel;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExameDaMateriaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ExameDaMateriaModel toModel(ExameDaMateria objeto) {
        return modelMapper.map(objeto, ExameDaMateriaModel.class);
    }

    public List<ExameDaMateriaModel> toCollectionModel(List<ExameDaMateria> exames) {
        return exames.stream()
                .map(exameDaMateria -> toModel(exameDaMateria))
                .collect(Collectors.toList());
    }
}
