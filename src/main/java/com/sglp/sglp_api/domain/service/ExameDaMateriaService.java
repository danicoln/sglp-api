package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ExameDaMateriaNaoEncontradoException;
import com.sglp.sglp_api.domain.exception.ExameExistenteException;
import com.sglp.sglp_api.domain.exception.ObjetoLaudoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.repository.ExameDaMateriaRepository;
import com.sglp.sglp_api.domain.repository.ObjetoLaudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExameDaMateriaService {

    private static final String EXAME_NAO_ENCONTRADO = "Exame com o ID %d não encontrado";
    private static final String OBJETO_NAO_ENCONTRADO = "Objeto com o ID %d não encontrado";
    private static final String EXAME_EXISTENTE = "Já existe um exame com ID associado ao laudo pericial %s";

    @Autowired
    private ExameDaMateriaRepository exameDaMateriaRepository;

    @Autowired
    private LaudoPericialService laudoPericialService;

    public ExameDaMateria obterExame(String laudoId) {
        LaudoPericial laudoPericial = laudoPericialService.buscarPorIdOuFalhar(laudoId);
        return laudoPericial.getExameDaMateria();
    }

    public ExameDaMateria buscar(String exameId) {
        return this.buscarOuFalhar(exameId);
    }

    @Transactional
    public ExameDaMateria salvar(String laudoId, ExameDaMateria exameDaMateria) {
        LaudoPericial laudo = laudoPericialService.buscarPorIdOuFalhar(laudoId);

        if(exameDaMateria.getId() == null || exameDaMateria.getId().isEmpty()) {

            if (laudo.getExameDaMateria() != null && !laudo.getExameDaMateria().getId().isEmpty()) {
                throw new ExameExistenteException(EXAME_EXISTENTE, laudoId);
            }
        }

        ExameDaMateria exameSalvo = exameDaMateriaRepository.save(exameDaMateria);
        laudo.setExameDaMateria(exameSalvo);
        laudoPericialService.salvar(laudo);
        return exameSalvo;

    }

    public ExameDaMateria buscarOuFalhar(String exameId) {
        return exameDaMateriaRepository.findById(exameId)
                .orElseThrow(() -> new ExameDaMateriaNaoEncontradoException(exameId));
    }

    public void remover(String laudoId, String exameId) {
        exameDaMateriaRepository.deleteById(exameId);
        removerExameDoLaudoById(laudoId);
    }

    public ExameDaMateria buscarPorId(String laudoId, String exameId) {
        LaudoPericial laudoPericial = laudoPericialService.buscarPorIdOuFalhar(laudoId);
        Optional<ExameDaMateria> exameOptional = exameDaMateriaRepository.findById(exameId);

        return exameOptional.get();
    }

    @Transactional
    public ExameDaMateria atualizar(String laudoId, String exameId, ExameDaMateria exame) {
        Optional<LaudoPericial> laudoOptional = laudoPericialService.buscar(laudoId);
        ExameDaMateria exameExistente = buscarOuFalhar(exameId);

        if (laudoOptional.isPresent()) {
            exameExistente.setDescricao(exame.getDescricao());
            exameExistente.setObjetosIds(exame.getObjetosIds());
            return salvar(laudoOptional.get().getId(), exameExistente);

        }
        return null;
    }

    private void removerExameDoLaudoById(String laudoId) {
        LaudoPericial laudoPericial = laudoPericialService.buscarPorIdOuFalhar(laudoId);
        laudoPericial.setExameDaMateria(null);
        laudoPericialService.salvar(laudoPericial);
    }

    public void atualizarObjetos(String exameId, List<String> objetosIds) {
        ExameDaMateria exameDaMateria = buscarOuFalhar(exameId);
        exameDaMateria.setObjetosIds(objetosIds);
        exameDaMateriaRepository.save(exameDaMateria);
    }
}
