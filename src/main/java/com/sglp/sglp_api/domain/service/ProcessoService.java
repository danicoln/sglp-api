package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ProcessoNaoEcontradoException;
import com.sglp.sglp_api.domain.model.Processo;
import com.sglp.sglp_api.domain.repository.ProcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProcessoService {

    private final ProcessoRepository processoRepository;

    @Transactional
    public Processo salvar(Processo processo) {
        return processoRepository.save(processo);
    }

    public List<Processo> listar() {
        return processoRepository.findAll();
    }

    public Processo buscar(String processoId) {
        Optional<Processo> processoOptional = processoRepository.findById(processoId);
        return processoOptional.orElse(null);
    }

    public Processo buscarPorIdOuFalhar(String processoId) {
        return processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNaoEcontradoException(processoId));
    }

    @Transactional
    public void remover(String processoId) {
        processoRepository.deleteById(processoId);
    }

    @Transactional
    public Processo atualizar(String processoId, Processo processo) {
        Processo processoExistente = buscarPorIdOuFalhar(processoId);
        BeanUtils.copyProperties(processo, processoExistente, "id, laudoId");
        return processoRepository.save(processoExistente);
    }
}
