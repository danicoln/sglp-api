package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.QuesitoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.Quesito;
import com.sglp.sglp_api.domain.repository.QuesitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuesitoService {

    @Autowired
    private QuesitoRepository quesitoRepository;


    public List<Quesito> listar() {
        return quesitoRepository.findAll();
    }

    public Quesito buscar(String quesitoId) {
        Optional<Quesito> obj = quesitoRepository.findById(quesitoId);

        if(obj.get().getId().equals(quesitoId)){
            return obj.get();
        }
        return null;
    }

    @Transactional
    public Quesito salvar(Quesito quesito) {
        return quesitoRepository.save(quesito);
    }

    public Quesito buscarOuFalhar(String quesitoId) {
        return quesitoRepository.findById(quesitoId)
                .orElseThrow(() -> new QuesitoNaoEncontradoException(quesitoId));
    }
}
