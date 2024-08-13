package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.PeritoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.Perito;
import com.sglp.sglp_api.domain.repository.PeritoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PeritoService {

    public static final String PERITO_NAO_ENCONTRADO = "Perito com o código %s não encontrado";

    private final PeritoRepository peritoRepository;

    public List<Perito> listar() {
        return peritoRepository.findAll();
    }

    @Transactional
    public Perito criar(Perito perito) {
        return peritoRepository.save(perito);
    }

    public Optional<Perito> buscarPorId(String id) {
        return peritoRepository.findById(id);
    }

    @Transactional
    public Perito atualizar(String id, Perito perito) {
        Perito peritoExistente = buscarPorIdOuFalhar(id);
        BeanUtils.copyProperties(perito, peritoExistente, "id");
        return peritoRepository.save(peritoExistente);
    }

    private Perito buscarPorIdOuFalhar(String id) {
        return peritoRepository.findById(id)
                .orElseThrow(() -> new PeritoNaoEncontradoException(PERITO_NAO_ENCONTRADO, id));
    }
}
