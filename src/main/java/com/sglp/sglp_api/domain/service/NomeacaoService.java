package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.NomeacaoExistenteException;
import com.sglp.sglp_api.domain.exception.NomeacaoNaoEncontradaException;
import com.sglp.sglp_api.domain.model.Nomeacao;
import com.sglp.sglp_api.domain.repository.NomeacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class NomeacaoService {

    public static final String NOMEACAO_COM_O_MESMO_PROCESSO = "Já existe uma nomeação com o mesmo processo";

    private final NomeacaoRepository nomeacaoRepository;

    public List<Nomeacao> listar() {
        return nomeacaoRepository.findAll();
    }

    public Nomeacao buscarPorIdOuFalhar(String nomeacaoId) {
        return nomeacaoRepository.findById(nomeacaoId)
                .orElseThrow(() -> new NomeacaoNaoEncontradaException(nomeacaoId));
    }

    @Transactional
    public Nomeacao salvar(Nomeacao nomeacao) {
        boolean isProcessoExistente = nomeacaoRepository.existsByProcessoNumero(nomeacao.getProcesso().getNumero());
        if(isProcessoExistente){
            throw new NomeacaoExistenteException(NOMEACAO_COM_O_MESMO_PROCESSO);
        }
        return nomeacaoRepository.save(nomeacao);
    }

    @Transactional
    public void remover(String nomeacaoId) {
        nomeacaoRepository.deleteById(nomeacaoId);
    }

    @Transactional
    public Nomeacao atualizar(String nomeacaoId, Nomeacao nomeacao) {
        Nomeacao nomeacaoExistente = buscarPorIdOuFalhar(nomeacaoId);
        BeanUtils.copyProperties(nomeacao, nomeacaoExistente, "id");
        return nomeacaoRepository.save(nomeacaoExistente);
    }
}
