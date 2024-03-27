package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.assembler.NomeacaoModelAssembler;
import com.sglp.sglp_api.api.disassembler.NomeacaoInputDisassembler;
import com.sglp.sglp_api.api.dto.input.NomeacaoInput;
import com.sglp.sglp_api.api.dto.model.NomeacaoModel;
import com.sglp.sglp_api.domain.model.Nomeacao;
import com.sglp.sglp_api.domain.service.NomeacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nomeacoes")
public class NomeacaoResource {

    @Autowired
    private NomeacaoService nomeacaoService;

    @Autowired
    private NomeacaoModelAssembler nomeacaoModelAssembler;

    @Autowired
    private NomeacaoInputDisassembler nomeacaoInputDisassembler;

    @GetMapping
    public List<NomeacaoModel> listar() {
        return nomeacaoModelAssembler.toCollectionModel(nomeacaoService.listar());
    }

    @GetMapping("/{nomeacaoId}")
    public NomeacaoModel buscar(@PathVariable String nomeacaoId) {
        Nomeacao nomeacao = nomeacaoService.buscarOuFalhar(nomeacaoId);

        return nomeacaoModelAssembler.toModel(nomeacao);
    }

    @PostMapping
    public ResponseEntity<NomeacaoModel> salvar(@RequestBody NomeacaoInput input) {
        Nomeacao nomeacao = nomeacaoInputDisassembler.toDomainObject(input);
        NomeacaoModel model = nomeacaoModelAssembler.toModel(nomeacaoService.salvar(nomeacao));

        return ResponseEntity.ok().body(model);
    }

    @PutMapping("/{nomeacaoId}")
    public NomeacaoModel atualizar(@PathVariable String nomeacaoId,
                                   @RequestBody NomeacaoInput input){
        Nomeacao nomeacaoAtual = nomeacaoService.buscarOuFalhar(nomeacaoId);
        nomeacaoInputDisassembler.copyToDomainObject(input, nomeacaoAtual);
        Nomeacao nomeacao = nomeacaoService.salvar(nomeacaoAtual);

        return nomeacaoModelAssembler.toModel(nomeacao);
    }

    @DeleteMapping("/{nomeacaoId}")
    public ResponseEntity<NomeacaoModel> remover(@PathVariable String nomeacaoId) {
        Nomeacao nomeacao = nomeacaoService.buscarOuFalhar(nomeacaoId);
        if(nomeacao.getId().equals(nomeacaoId)) {
            nomeacaoService.remover(nomeacaoId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
