package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.LaudoPericialNaoEncontradoException;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.repository.LaudoPericialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaudoPericialService {

    @Autowired
    private LaudoPericialRepository laudoPericialRepository;

    public LaudoPericial salvar(LaudoPericial laudoPericial) {
        //TODO: implementar a busca dos objetos que compoem o laudo pericial
        /*
        Long objetoLaudoId = laudoPericial.getObjeto().getId();
        Long examePericiaId = laudoPericial.getExameDaMateria().getId();
        List<Quesito> quesitos = laudoPericial.getQuesitos();
        */

        return laudoPericialRepository.save(laudoPericial);
    }

    public List<LaudoPericial> listar() {
        return laudoPericialRepository.findAll();
    }

    public LaudoPericial buscarOuFalhar(Long laudoId) {
        return laudoPericialRepository.findById(laudoId)
                .orElseThrow(() -> new LaudoPericialNaoEncontradoException(laudoId));
    }
}
