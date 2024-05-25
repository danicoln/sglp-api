package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.LaudoPericialNaoEncontradoException;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.repository.LaudoPericialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LaudoPericialService {

    public static final String LAUDO_PERICIAL_NAO_ENCONTRADO = "Laudo Pericial com o ID %s não encontrado";

    @Autowired
    private LaudoPericialRepository laudoPericialRepository;

    @Transactional
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

    public LaudoPericial buscarOuFalhar(String laudoId) {
        return laudoPericialRepository.findById(laudoId)
                .orElseThrow(() -> new LaudoPericialNaoEncontradoException(laudoId));
    }

    public Optional<LaudoPericial> buscar(String laudoId) {
        return this.laudoPericialRepository.findById(laudoId);
    }

    @Transactional
    public LaudoPericial atualizar(String laudoId, LaudoPericial laudo) {
        LaudoPericial laudoExistente = validarLaudo(laudoId);
        laudoExistente.setProcesso(laudo.getProcesso());
        laudoExistente.setConclusao(laudo.getConclusao());
        laudoExistente.setHistorico(laudo.getHistorico());
        laudoExistente.setIntroducao(laudo.getIntroducao());
        laudoExistente.setDataDoLaudo(laudo.getDataDoLaudo());
        laudoExistente.setMetodologiaAplicada(laudo.getMetodologiaAplicada());
        laudoExistente.setObjetivo(laudo.getObjetivo());
        laudoExistente.setExameDaMateria(laudo.getExameDaMateria());
//        laudoExistente.setObjetos(laudo.getObjetos());
//        laudoExistente.setQuesitos(laudo.getQuesitos());

        return salvar(laudoExistente);
    }

    private LaudoPericial validarLaudo(String laudoId) {
        return this.buscar(laudoId)
                .orElseThrow(() -> new LaudoPericialNaoEncontradoException(LAUDO_PERICIAL_NAO_ENCONTRADO, laudoId));
    }
}
