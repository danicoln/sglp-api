package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.QuesitoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.model.Quesito;
import com.sglp.sglp_api.domain.repository.QuesitoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuesitoService {

    public static final String QUESITO_NAO_ENCONTRADO = "Quesito não encontrado no laudo de ID %s";

    private final QuesitoRepository quesitoRepository;
    private final LaudoPericialService laudoService;

    public List<Quesito> listar(String laudoId) {
        return quesitoRepository.findAllByLaudoId(laudoId);
    }

    public Optional<Quesito> buscar(String quesitoId) {
        return quesitoRepository.findById(quesitoId);
    }

    @Transactional
    public Quesito salvar(String laudoId, Quesito quesito) {
        quesito.setLaudoId(laudoId);
        Quesito quesitoSalvo = quesitoRepository.save(quesito);
        atualizarQuesitoNoLaudo(laudoId, quesito);
        return quesitoSalvo;
    }

    @Transactional
    public Quesito atualizar(String quesitoId, Quesito quesito) {
        Quesito quesitoExistente = buscarPorIdOuFalhar(quesitoId);
        BeanUtils.copyProperties(quesito, quesitoExistente, "id");
        Quesito quesitoAtualizado = quesitoRepository.save(quesitoExistente);
        atualizarQuesitoNoLaudo(quesitoExistente.getLaudoId(), quesitoAtualizado);
        return quesitoAtualizado;
    }

    private void atualizarQuesitoNoLaudo(String laudoId, Quesito quesito) {
        LaudoPericial laudo = laudoService.buscarPorIdOuFalhar(laudoId);

        laudo.getQuesitos().removeIf(q -> q.getId().equals(quesito.getId()));
        laudo.getQuesitos().add(quesito);
        laudoService.atualizar(laudoId, laudo);
    }

    public Quesito buscarPorIdOuFalhar(String quesitoId) {
        return quesitoRepository.findById(quesitoId)
                .orElseThrow(() -> new QuesitoNaoEncontradoException(quesitoId));
    }

    @Transactional
    public void remover(String quesitoId) {
        quesitoRepository.deleteById(quesitoId);
    }
}
