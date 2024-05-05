package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.ExameDaMateriaNaoEncontradoException;
import com.sglp.sglp_api.domain.exception.ObjetoLaudoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.Documento;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.repository.ObjetoLaudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ObjetoLaudoService {

    private static final String EXAME_NAO_ENCONTRADO = "Exame com o ID %d não encontrado";
    private static final String OBJETO_NAO_ENCONTRADO = "Objeto com o ID %d não encontrado";
    @Autowired
    private ObjetoLaudoRepository objetoLaudoRepository;

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private ExameDaMateriaService exameDaMateriaService;

    public List<ObjetoLaudo> listar(String exameId) {
        Optional<ExameDaMateria> exameDaMateriaOptional = exameDaMateriaService.buscarPorId(exameId);

        if(exameDaMateriaOptional.isEmpty()) {
            return Collections.emptyList();
        }

        ExameDaMateria exame = exameDaMateriaOptional.get();
        List<String> objetosIds = exame.getObjetosIds();

        if(objetosIds == null || objetosIds.isEmpty()) {
            return Collections.emptyList();
        }

        return objetoLaudoRepository.findAllById(objetosIds);
    }

    public ObjetoLaudo buscarPorId(String exameId, String objetoId) {
        ExameDaMateria exame = validarExistenciaExame(exameId);
        ObjetoLaudo objetoEncontrado = validarExistenciaObjeto(objetoId);

        if (!exame.getId().equals(objetoEncontrado.getExameDaMateriaId())) {
            throw new ObjetoLaudoNaoEncontradoException(OBJETO_NAO_ENCONTRADO, objetoId);
        }
        return objetoEncontrado;
    }

    public ObjetoLaudo buscarOuFalhar(String objetoId) {
        return objetoLaudoRepository.findById(objetoId)
                .orElseThrow(() -> new ObjetoLaudoNaoEncontradoException(objetoId));
    }

    @Transactional
    public ObjetoLaudo salvar(String exameId, ObjetoLaudo objetoLaudo) {
        ExameDaMateria exame = exameDaMateriaService.buscarOuFalhar(exameId);
        objetoLaudo.setExameDaMateriaId(exame.getId());

        ObjetoLaudo objetoSalvo = objetoLaudoRepository.save(objetoLaudo);
        exame.getObjetosIds().add(objetoSalvo.getId());

        Documento documento = this.documentoService.salvar(objetoSalvo.getDocumento());
        return this.objetoLaudoRepository.save(objetoLaudo);
    }

    @Transactional
    private Documento salvarDocumento(Documento documento) {
        return documentoService.salvar(documento);
    }

    public void remover(String objetoId) {
        objetoLaudoRepository.deleteById(objetoId);
    }

    public Optional<ExameDaMateria> buscarExame(String exameId) {
        Optional<ExameDaMateria> exameDaMateria = exameDaMateriaService.buscarPorId(exameId);
        if (exameDaMateria.isPresent()) {
            return exameDaMateria;
        }
        return null;
    }

    @Transactional
    public ObjetoLaudo atualizar(String exameId, String objetoId, ObjetoLaudo objetoLaudo) {

        ExameDaMateria exame = validarExistenciaExame(exameId);
        ObjetoLaudo objetoExistente = validarExistenciaObjeto(objetoId);
        objetoExistente.atualizarExame(objetoLaudo);
        return salvar(exame.getId(), objetoExistente);
    }

    private ObjetoLaudo validarExistenciaObjeto(String objetoId) {
        return objetoLaudoRepository.findById(objetoId)
                .orElseThrow(() -> new ObjetoLaudoNaoEncontradoException(OBJETO_NAO_ENCONTRADO, objetoId));
    }

    private ExameDaMateria validarExistenciaExame(String exameId) {
        return exameDaMateriaService.buscarPorId(exameId)
                .orElseThrow(() -> new ExameDaMateriaNaoEncontradoException(EXAME_NAO_ENCONTRADO, exameId));
    }
}
