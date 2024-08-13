package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.NomeacaoInput;
import com.sglp.sglp_api.api.dto.model.NomeacaoModel;
import com.sglp.sglp_api.api.mapper.NomeacaoMapper;
import com.sglp.sglp_api.domain.exception.NomeacaoNaoEncontradaException;
import com.sglp.sglp_api.domain.model.Nomeacao;
import com.sglp.sglp_api.domain.service.NomeacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/nomeacoes")
public class NomeacaoResource {

    private final NomeacaoService nomeacaoService;
    private final NomeacaoMapper mapper;

    @GetMapping
    public ResponseEntity<List<NomeacaoModel>> listar() {
        List<Nomeacao> nomeacoes = nomeacaoService.listar();
        return ResponseEntity.ok(mapper.toModelList(nomeacoes));
    }

    @GetMapping("/{nomeacaoId}")
    public ResponseEntity<NomeacaoModel> buscar(@PathVariable String nomeacaoId) {
        Nomeacao nomeacao = nomeacaoService.buscarPorIdOuFalhar(nomeacaoId);
        return ResponseEntity.ok(mapper.toModel(nomeacao));
    }

    @PostMapping
    public ResponseEntity<NomeacaoModel> salvar(@RequestBody NomeacaoInput input) {
        Nomeacao nomeacao = mapper.toEntity(input);
        NomeacaoModel model = mapper.toModel(nomeacaoService.salvar(nomeacao));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{nomeacaoId}")
    public ResponseEntity<NomeacaoModel> atualizar(@PathVariable String nomeacaoId,
                                                   @RequestBody NomeacaoInput input) {
        Nomeacao nomeacaoAtual = mapper.toEntity(input);
        Nomeacao nomeacaoAtualizada = nomeacaoService.atualizar(nomeacaoId, nomeacaoAtual);
        NomeacaoModel model = mapper.toModel(nomeacaoAtualizada);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{nomeacaoId}")
    public ResponseEntity<NomeacaoModel> remover(@PathVariable String nomeacaoId) {
        try {
            nomeacaoService.remover(nomeacaoId);
            return ResponseEntity.noContent().build();
        } catch (NomeacaoNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
