package com.sglp.sglp_api.api.assembler;

import com.sglp.sglp_api.api.dto.model.NomeacaoModel;
import com.sglp.sglp_api.domain.model.Nomeacao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NomeacaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public NomeacaoModel toModel(Nomeacao nomeacao) {
        return modelMapper.map(nomeacao, NomeacaoModel.class);
    }

    public List<NomeacaoModel> toCollectionModel(List<Nomeacao> nomeacoes) {
        return nomeacoes.stream()
                .map(nomeacao -> toModel(nomeacao))
                .collect(Collectors.toList());
    }
}
