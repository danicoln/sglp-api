package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ExameDaMateriaNaoEncontradoException;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.repository.ExameDaMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExameDaMateriaService {

    @Autowired
    private ExameDaMateriaRepository exameDaMateriaRepository;

    @Autowired
    private DocumentoService documentoService;
    @Autowired
    private ObjetoLaudoService objetoLaudoService;

    public List<ExameDaMateria> listar() {
        return exameDaMateriaRepository.findAll();
    }

    public ExameDaMateria buscar(String exameId) {
        return this.buscarOuFalhar(exameId);
    }

    @Transactional
    public ExameDaMateria salvar(ExameDaMateria exameDaMateria) {

        ExameDaMateria exameSalvo = exameDaMateriaRepository.save(exameDaMateria);
        String exameId = exameSalvo.getId();

        for(ObjetoLaudo objeto : exameDaMateria.getObjetos()) {
            objeto.setExameDaMateriaId(exameId);
            objetoLaudoService.salvar(objeto);
        }

        return exameDaMateriaRepository.save(exameSalvo);
    }

    public ExameDaMateria buscarOuFalhar(String exameId) {
        return exameDaMateriaRepository.findById(exameId)
                .orElseThrow(() -> new ExameDaMateriaNaoEncontradoException(exameId));
    }

    public void remover(String exameId) {
        exameDaMateriaRepository.deleteById(exameId);
    }

    public void salvarObjetosDoExame(ExameDaMateria exameDaMateria) {
    }
}
