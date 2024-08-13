package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.model.Quesito;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LaudoDataService {

    private final LaudoPericialService laudoPericialService;
    private final QuesitoService quesitoService;
    private final ExameDaMateriaService exameService;
    private final ObjetoLaudoService objetoService;
    private final AdvogadoService advogadoService;
    private final NomeacaoService nomeacaoService;
    private final ProcessoService processoService;

    public LaudoPericial findLaudoPericialById(String id) {
        return laudoPericialService.buscarPorIdOuFalhar(id);
    }

    public Quesito findQuesitoById(String id){
        return quesitoService.buscarPorIdOuFalhar(id);
    }

    public ExameDaMateria findExameDaMateriaById(String id) {
        return exameService.buscarOuFalhar(id);
    }

    public ObjetoLaudo findObjetoLaudoById(String id){
        return objetoService.buscarPorIdOuFalhar(id);
    }
}
