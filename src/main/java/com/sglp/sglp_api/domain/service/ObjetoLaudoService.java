package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ObjetoLaudoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.Documento;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.repository.ObjetoLaudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjetoLaudoService {

    @Autowired
    private ObjetoLaudoRepository objetoLaudoRepository;

    @Autowired
    private DocumentoService documentoService;

    public List<ObjetoLaudo> listar() {
        return objetoLaudoRepository.findAll();
    }

    public ObjetoLaudo buscar(String objetoId) {
        Optional<ObjetoLaudo> obj = objetoLaudoRepository.findById(objetoId);
        if (obj.isPresent()) {
            return obj.get();
        }
        return null;
    }

    public ObjetoLaudo buscarOuFalhar(String objetoId) {
        return objetoLaudoRepository.findById(objetoId)
                .orElseThrow(() -> new ObjetoLaudoNaoEncontradoException(objetoId));
    }

    @Transactional
    public ObjetoLaudo salvar(ObjetoLaudo objetoLaudo) {

        Documento documento = objetoLaudo.getDocumento();
        this.documentoService.salvar(documento);

        return objetoLaudoRepository.save(objetoLaudo);
    }

    public void remover(String objetoId) {
        objetoLaudoRepository.deleteById(objetoId);
    }
}
