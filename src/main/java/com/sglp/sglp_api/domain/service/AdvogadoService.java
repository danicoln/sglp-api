package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.AdvogadoExistenteException;
import com.sglp.sglp_api.domain.exception.AdvogadoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.Advogado;
import com.sglp.sglp_api.domain.repository.AdvogadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AdvogadoService {

    public static final String ADVOGADO_EXISTENTE = "Já existe um advogado cadastrado com o mesmo e-mail";

    private final AdvogadoRepository repository;

    public List<Advogado> listar() {
        return repository.findAll();
    }

    @Transactional
    public Advogado salvar(Advogado advogado) {
        boolean isAdvogadoExistente = repository.existsByEmail(advogado.getEmail());
        if (isAdvogadoExistente) {
            throw new AdvogadoExistenteException(ADVOGADO_EXISTENTE);
        }
        return repository.save(advogado);
    }

    @Transactional
    public Advogado atualizar(String advogadoId, Advogado advogado) {
        Advogado advExistente = buscarPorIdOuFalhar(advogadoId);
        BeanUtils.copyProperties(advogado, advExistente, "id");
        return repository.save(advExistente);
    }

    @Transactional
    public void remover(String advogadoId) {
        repository.deleteById(advogadoId);
    }

    public Advogado buscarPorIdOuFalhar(String advogadoId) {
        return repository.findById(advogadoId)
                .orElseThrow(() -> new AdvogadoNaoEncontradoException(advogadoId));
    }
}
