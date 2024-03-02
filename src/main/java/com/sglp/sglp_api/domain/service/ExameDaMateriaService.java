package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ExameDaMateriaNaoEncontradoException;
import com.sglp.sglp_api.domain.model.Documento;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.repository.ExameDaMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ExameDaMateriaService {

    @Autowired
    private ExameDaMateriaRepository exameDaMateriaRepository;

    @Autowired
    private DocumentoService documentoService;

    public List<ExameDaMateria> listar() {
        return exameDaMateriaRepository.findAll();
    }

    public ExameDaMateria buscar(String exameId) {
        Optional<ExameDaMateria> obj = exameDaMateriaRepository.findById(exameId);

        if (obj.get().getId().equals(exameId)) {
            return obj.get();
        }
        return null;
    }

    @Transactional
    public ExameDaMateria salvar(ExameDaMateria exameDaMateria) {

        if (exameDaMateria != null && exameDaMateria.getObjetos() != null) {
            for (ObjetoLaudo novoObjeto : exameDaMateria.getObjetos()) {

                processarObjetoLaudo(novoObjeto);
            }
        }
        return exameDaMateriaRepository.save(exameDaMateria);
    }

    public ExameDaMateria buscarOuFalhar(String exameId) {
        return exameDaMateriaRepository.findById(exameId)
                .orElseThrow(() -> new ExameDaMateriaNaoEncontradoException(exameId));
    }

    private void processarObjetoLaudo(ObjetoLaudo novoObjeto) {

        if (novoObjeto != null && novoObjeto.getDocumentos() != null) {
            Iterator<Documento> iterator = novoObjeto.getDocumentos().iterator();

            processarDocumento(iterator);
        }
    }

    private void processarDocumento(Iterator<Documento> iterator) {

        while (iterator.hasNext()) {
            Documento documento = iterator.next();
            documentoService.salvar(documento);
        }
    }
}
