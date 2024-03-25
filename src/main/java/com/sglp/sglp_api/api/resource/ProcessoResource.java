package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.assembler.ProcessoModelAssembler;
import com.sglp.sglp_api.api.disassembler.ProcessoInputDisassembler;
import com.sglp.sglp_api.api.dto.input.ProcessoInput;
import com.sglp.sglp_api.api.dto.model.ProcessoModel;
import com.sglp.sglp_api.domain.model.Processo;
import com.sglp.sglp_api.domain.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processos")
public class ProcessoResource {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ProcessoModelAssembler processoModelAssembler;

    @Autowired
    private ProcessoInputDisassembler processoInputDisassembler;

    @GetMapping
    public List<ProcessoModel> listar() {
        return processoModelAssembler.toCollectionModel(processoService.listar());
    }

    @GetMapping("/{processoId}")
    private ResponseEntity<ProcessoModel> buscar(@PathVariable String processoId) {
        Processo processo = processoService.buscar(processoId);

        if (processo != null) {
            ProcessoModel model = processoModelAssembler.toModel(processo);
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProcessoModel> salvar(@RequestBody ProcessoInput input) {
        Processo processo = processoInputDisassembler.toDomainObject(input);
        ProcessoModel model = processoModelAssembler.toModel(processoService.salvar(processo));
        return ResponseEntity.ok().body(model);
    }

    @PutMapping("/{processoId}")
    public ProcessoModel atualizar(@PathVariable String processoId,
                                   @RequestBody ProcessoInput input) {
        Processo processoAtual = processoService.buscarOuFalhar(processoId);
        processoInputDisassembler.copyToDomainObject(input, processoAtual);

        Processo processo = processoService.salvar(processoAtual);

        return processoModelAssembler.toModel(processo);
    }

    @DeleteMapping("/{processoId}")
    public ResponseEntity<ProcessoModel> remover(@PathVariable String processoId) {
        Processo processo = processoService.buscarOuFalhar(processoId);
        if (processo.getId().equals(processoId)) {
            processoService.remover(processoId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
