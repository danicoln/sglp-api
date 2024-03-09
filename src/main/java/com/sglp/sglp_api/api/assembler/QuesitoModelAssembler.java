package com.sglp.sglp_api.api.assembler;

import com.sglp.sglp_api.api.dto.model.QuesitoModel;
import com.sglp.sglp_api.domain.model.Quesito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuesitoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public QuesitoModel toModel(Quesito quesito) {
        return modelMapper.map(quesito, QuesitoModel.class);
    }

    public List<QuesitoModel> toCollectionModel(List<Quesito> quesitos) {
        return quesitos.stream()
                .map(quesito -> toModel(quesito))
                .collect(Collectors.toList());
    }
}
