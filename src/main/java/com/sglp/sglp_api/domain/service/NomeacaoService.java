package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.NomeacaoNaoEncontradaException;
import com.sglp.sglp_api.domain.model.Nomeacao;
import com.sglp.sglp_api.domain.model.Processo;
import com.sglp.sglp_api.domain.repository.NomeacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NomeacaoService {

    @Autowired
    private NomeacaoRepository nomeacaoRepository;

    public List<Nomeacao> listar() {
        return nomeacaoRepository.findAll();
    }

    public Nomeacao buscarOuFalhar(String nomeacaoId) {
        return nomeacaoRepository.findById(nomeacaoId)
                .orElseThrow(() -> new NomeacaoNaoEncontradaException(nomeacaoId));
    }

    @Transactional
    public Nomeacao salvar(Nomeacao nomeacao) {
        //TODO: implementar a busca dos objetos que compoem a nomeação

        Processo processo = new Processo();
        processo.setAssunto(nomeacao.getProcesso().getAssunto());
        processo.setComarca(nomeacao.getProcesso().getComarca());
        processo.setVara(nomeacao.getProcesso().getVara());
        processo.setParteAutora(nomeacao.getProcesso().getParteAutora());
        processo.setParteReu(nomeacao.getProcesso().getParteReu());

        return nomeacaoRepository.save(nomeacao);
    }

    public void remover(String nomeacaoId) {
        nomeacaoRepository.deleteById(nomeacaoId);
    }
}
