package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.model.Documento;
import com.sglp.sglp_api.domain.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public Documento salvar(Documento documento) {
        return documentoRepository.save(documento);
    }
}
