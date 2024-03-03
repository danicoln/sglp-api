package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ProcessoNaoEcontradoException;
import com.sglp.sglp_api.domain.model.Processo;
import com.sglp.sglp_api.domain.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public Processo salvar(Processo processo) {
        return processoRepository.save(processo);
    }


    public List<Processo> listar() {
        return processoRepository.findAll();
    }

    public Processo buscar(String processoId) {
        Optional<Processo> processos = processoRepository.findById(processoId);
        if(processos.get().getId().equals(processoId)) {
            return processos.get();
        }
        return null;
    }

    public Processo buscarOuFalhar(String processoId) {
        return processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNaoEcontradoException(processoId));
    }
}
