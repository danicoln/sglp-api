package com.sglp.sglp_api.api.assembler;

import com.sglp.sglp_api.api.dto.model.ProcessoModel;
import com.sglp.sglp_api.domain.model.Processo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProcessoModel toModel(Processo objeto) {
        return modelMapper.map(objeto, ProcessoModel.class);
    }

    public List<ProcessoModel> toCollectionModel(List<Processo> processos) {
        return processos.stream()
                .map(processo -> toModel(processo))
                .collect(Collectors.toList());
    }
}
