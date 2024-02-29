package com.sglp.sglp_api.api.assembler;

import com.sglp.sglp_api.api.dto.model.PeritoModel;
import com.sglp.sglp_api.domain.model.Perito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PeritoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PeritoModel toModel(Perito perito) {
        return modelMapper.map(perito, PeritoModel.class);
    }

    public List<PeritoModel> toCollectionModel(List<Perito> peritos) {
        return peritos.stream()
                .map(perito -> toModel(perito))
                .collect(Collectors.toList());
    }
}
