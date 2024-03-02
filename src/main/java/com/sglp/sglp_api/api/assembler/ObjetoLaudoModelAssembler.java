package com.sglp.sglp_api.api.assembler;

import com.sglp.sglp_api.api.dto.model.ObjetoLaudoModel;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjetoLaudoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ObjetoLaudoModel toModel(ObjetoLaudo objeto) {
        return modelMapper.map(objeto, ObjetoLaudoModel.class);
    }

    public List<ObjetoLaudoModel> toCollectionModel(List<ObjetoLaudo> objetos){
        return objetos.stream()
                .map(objetoLaudo -> toModel(objetoLaudo))
                .collect(Collectors.toList());
    }
}
