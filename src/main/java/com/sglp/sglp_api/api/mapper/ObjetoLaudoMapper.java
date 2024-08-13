package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.ObjetoLaudoInput;
import com.sglp.sglp_api.api.dto.model.ObjetoLaudoModel;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObjetoLaudoMapper {

    private final ModelMapper modelMapper;

    public ObjetoLaudo toEntity(ObjetoLaudoInput input) {
        return modelMapper.map(input, ObjetoLaudo.class);
    }

    public ObjetoLaudoModel toModel(ObjetoLaudo obj) {
        return modelMapper.map(obj, ObjetoLaudoModel.class);
    }

    public List<ObjetoLaudoModel> toModelList(List<ObjetoLaudo> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }

}
