package com.sglp.sglp_api.domain.service;

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
        ExameDaMateria exame = exameDaMateriaService.buscarOuFalhar(exameId);

        if (exame == null) {
            return Collections.emptyList();
        }

        List<String> objetosIds = exame.getObjetosIds();

        if (objetosIds == null || objetosIds.isEmpty()) {
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
    public ObjetoLaudo salvar(String exameId, ObjetoLaudo objeto) {
        ExameDaMateria exame = exameDaMateriaService.buscarOuFalhar(exameId);
        objeto.setExameDaMateriaId(exame.getId());

        Documento documento = objeto.getDocumento();
        Documento documentoSalvo = documentoService.salvar(documento);
        objeto.setDocumento(documentoSalvo);

        ObjetoLaudo objetoSalvo = objetoLaudoRepository.save(objeto);
        exame.getObjetosIds().add(objeto.getId());
        exameDaMateriaService.atualizarObjetos(exame.getId(), exame.getObjetosIds());

        return objetoSalvo;
    }

    @Transactional
    private Documento salvarDocumento(Documento documento) {
        return documentoService.salvar(documento);
    }

    public void remover(String objetoId) {
        objetoLaudoRepository.deleteById(objetoId);
    }

    public ExameDaMateria buscarExame(String exameId) {
        return exameDaMateriaService.buscarOuFalhar(exameId);
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
        return exameDaMateriaService.buscarOuFalhar(exameId);
    }
}
