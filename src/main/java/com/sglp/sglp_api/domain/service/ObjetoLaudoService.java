package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ObjetoLaudoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.repository.ObjetoLaudoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ObjetoLaudoService {

    private static final String EXAME_NAO_ENCONTRADO = "Exame com o ID %d não encontrado";
    private static final String OBJETO_NAO_ENCONTRADO = "Objeto com o ID %d não encontrado";
    @Autowired
    private ObjetoLaudoRepository objetoLaudoRepository;

    @Autowired
    private ExameDaMateriaService exameDaMateriaService;

    public List<ObjetoLaudo> listar(String exameId) {
        return objetoLaudoRepository.findAllByExameDaMateriaId(exameId);
    }

    public ObjetoLaudo buscarPorId(String exameId, String objetoId) {
        ExameDaMateria exame = exameDaMateriaService.buscarOuFalhar(exameId);
        ObjetoLaudo objetoEncontrado = buscarPorIdOuFalhar(objetoId);

        if (!exame.getId().equals(objetoEncontrado.getExameDaMateriaId())) {
            throw new ObjetoLaudoNaoEncontradoException(OBJETO_NAO_ENCONTRADO, objetoId);
        }
        return objetoEncontrado;
    }

    public ObjetoLaudo buscarPorIdOuFalhar(String objetoId) {
        return objetoLaudoRepository.findById(objetoId)
                .orElseThrow(() -> new ObjetoLaudoNaoEncontradoException(objetoId));
    }

    @Transactional
    public ObjetoLaudo salvar(String exameId, ObjetoLaudo objeto) {
        ExameDaMateria exame = exameDaMateriaService.buscarOuFalhar(exameId);
        adicionaNaListaObjetoIds(objeto, exame);
        return objetoLaudoRepository.save(objeto);
    }

    @Transactional
    public void remover(String objetoId) {
        objetoLaudoRepository.deleteById(objetoId);
    }

    public ExameDaMateria buscarExame(String exameId) {
        return exameDaMateriaService.buscarOuFalhar(exameId);
    }

    @Transactional
    public ObjetoLaudo atualizar(String objetoId, ObjetoLaudo objetoLaudo) {
        ObjetoLaudo objetoExistente = buscarPorIdOuFalhar(objetoId);
        BeanUtils.copyProperties(objetoLaudo, objetoExistente, "id");
        return objetoLaudoRepository.save(objetoExistente);
    }

    private void adicionaNaListaObjetoIds(ObjetoLaudo objeto, ExameDaMateria exame) {
        exame.getObjetosIds().add(objeto.getId());
        exameDaMateriaService.atualizarObjetos(exame.getId(), exame.getObjetosIds());
    }

}
