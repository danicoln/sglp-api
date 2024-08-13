package com.sglp.sglp_api.api.mapper;

import com.sglp.sglp_api.api.dto.input.NomeacaoInput;
import com.sglp.sglp_api.api.dto.model.NomeacaoModel;
import com.sglp.sglp_api.domain.model.Nomeacao;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NomeacaoMapper {

    private final ModelMapper mapper;

    public Nomeacao toEntity(NomeacaoInput input) {
        return mapper.map(input, Nomeacao.class);
    }

    public NomeacaoModel toModel(Nomeacao obj) {
        return mapper.map(obj, NomeacaoModel.class);
    }

    public List<NomeacaoModel> toModelList(List<Nomeacao> objetos) {
        return objetos.stream()
                .map(this::toModel)
                .toList();
    }
}
