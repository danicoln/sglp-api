package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.ProcessoInput;
import com.sglp.sglp_api.api.dto.model.ProcessoModel;
import com.sglp.sglp_api.domain.model.Processo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProcessoMapper {

    private final ModelMapper mapper;

    public Processo toEntity(ProcessoInput input) {
        return mapper.map(input, Processo.class);
    }

    public ProcessoModel toModel(Processo obj) {
        return mapper.map(obj, ProcessoModel.class);
    }

    public List<ProcessoModel> toModelList(List<Processo> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }
}
