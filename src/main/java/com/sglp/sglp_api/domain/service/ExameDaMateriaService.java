package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ExameDaMateriaNaoEncontradoException;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.repository.ExameDaMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExameDaMateriaService {

    private static final String EXAME_NAO_ENCONTRADO = "Exame com o ID %d não encontrado";

    @Autowired
    private ExameDaMateriaRepository exameDaMateriaRepository;

    public List<ExameDaMateria> listar() {
        return exameDaMateriaRepository.findAll();
    }

    public ExameDaMateria buscar(String exameId) {
        return this.buscarOuFalhar(exameId);
    }

    @Transactional
    public ExameDaMateria salvar(ExameDaMateria exameDaMateria) {
        if (exameDaMateria.getObjetos() == null) {
            exameDaMateria.setObjetos(new ArrayList<>());
        }
        ExameDaMateria exameSalvo = exameDaMateriaRepository.save(exameDaMateria);
        String exameId = exameSalvo.getId();

        for (ObjetoLaudo obj : exameDaMateria.getObjetos()) {
            obj.setExameDaMateriaId(exameId);
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

    public Optional<ExameDaMateria> buscarPorId(String exameId) {
        return exameDaMateriaRepository.findById(exameId);
    }

    @Transactional
    public ExameDaMateria atualizar(String exameId, ExameDaMateria exame) {
        ExameDaMateria exameExistente = validarExame(exameId);
        exameExistente.setDescricao(exame.getDescricao());

        List<ObjetoLaudo> objetosAtualizados = new ArrayList<>();
        for (ObjetoLaudo objeto : exame.getObjetos()) {
            ObjetoLaudo objetoExistente = exameExistente.getObjetos().stream()
                    .filter(obj -> obj.getId().equals(objeto.getId()))
                    .findFirst()
                    .orElse(null);

            if (objetoExistente != null) {
                objetoExistente.atualizarExame(objeto);
                objetosAtualizados.add(objetoExistente);
            } else {
                objeto.setExameDaMateriaId(exameId);
                objetosAtualizados.add(objeto);
            }
        }
        exameExistente.setObjetos(objetosAtualizados);

        return salvar(exameExistente);
    }

    private ExameDaMateria validarExame(String exameId) {
        return this.buscarPorId(exameId)
                .orElseThrow(() -> new ExameDaMateriaNaoEncontradoException(EXAME_NAO_ENCONTRADO, exameId));
    }
}
