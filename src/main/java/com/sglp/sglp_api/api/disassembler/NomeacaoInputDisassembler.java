package com.sglp.sglp_api.api.disassembler;

import com.sglp.sglp_api.api.dto.input.NomeacaoInput;
import com.sglp.sglp_api.domain.model.Nomeacao;
import com.sglp.sglp_api.domain.model.Processo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NomeacaoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Nomeacao toDomainObject(NomeacaoInput input) {
        return modelMapper.map(input, Nomeacao.class);
    }

    public void copyToDomainObject(NomeacaoInput input, Nomeacao nomeacao) {
        nomeacao.setProcesso(new Processo());
        modelMapper.map(input, nomeacao);
    }
}
