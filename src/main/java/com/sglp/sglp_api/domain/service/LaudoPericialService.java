package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.domain.exception.LaudoPericialNaoEncontradoException;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.model.Status;
import com.sglp.sglp_api.domain.repository.LaudoPericialRepository;
import com.sglp.sglp_api.domain.service.utils.NumeroLaudoGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LaudoPericialService {

    public static final String PROCESSO_AUSENTE = "Número do processo não pode ser vazio";
    private static final String DATA_PATTERN = "yyyyMMdd";
    private static final String NUMERACAO_PATTERN = "%s-%04d";

    private final LaudoPericialRepository laudoPericialRepository;
    private final NumeroLaudoGenerator generator;

    @Transactional
    public LaudoPericial salvar(LaudoPericial laudoPericial) {
        String numeroProcesso = laudoPericial.getProcesso().getNumero();

        if(numeroProcesso == null || numeroProcesso.isEmpty()) {
            throw new IllegalArgumentException(PROCESSO_AUSENTE);
        }

        if(laudoPericial.getNumero() == null) {
            laudoPericial.setNumero(generator.gerarNumeroDoLaudo(LocalDateTime.now()));
        }

        if(laudoPericial.getStatus() == null){
            laudoPericial.setStatus(Status.NAO_INICIADO);
        }

        laudoPericial.setAtivo(true);
        return laudoPericialRepository.save(laudoPericial);
    }

    public List<LaudoPericial> listar() {
        return laudoPericialRepository.findAll();
    }

    public List<LaudoPericial> listarAtivos(boolean ativo) {
        return laudoPericialRepository.findAllByAtivo(ativo);
    }

    public LaudoPericial buscarPorIdOuFalhar(String laudoId) {
        return laudoPericialRepository.findById(laudoId)
                .orElseThrow(() -> new LaudoPericialNaoEncontradoException(laudoId));
    }

    public Optional<LaudoPericial> buscar(String laudoId) {
        return this.laudoPericialRepository.findById(laudoId);
    }

    @Transactional
    public LaudoPericial atualizar(String laudoId, LaudoPericial laudo) {
        LaudoPericial laudoExistente = buscarPorIdOuFalhar(laudoId);
        BeanUtils.copyProperties(laudo, laudoExistente, "id", "numero", "ativo", "exameDaMateria");
        laudoExistente.setStatus(laudo.getStatus());
        return laudoPericialRepository.save(laudoExistente);
    }

    public void ativar(String laudoId) {
        LaudoPericial laudoAtualizado = buscarPorIdOuFalhar(laudoId);
        laudoAtualizado.setAtivo(true);
        laudoPericialRepository.save(laudoAtualizado);
    }

    public void desativar(String laudoId) {
        LaudoPericial laudoAtualizado = buscarPorIdOuFalhar(laudoId);
        laudoAtualizado.setAtivo(false);
        laudoPericialRepository.save(laudoAtualizado);
    }
}
