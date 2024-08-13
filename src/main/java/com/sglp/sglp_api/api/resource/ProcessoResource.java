package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.ProcessoInput;
import com.sglp.sglp_api.api.dto.model.ProcessoModel;
import com.sglp.sglp_api.api.mapper.ProcessoMapper;
import com.sglp.sglp_api.domain.exception.ProcessoNaoEcontradoException;
import com.sglp.sglp_api.domain.model.Processo;
import com.sglp.sglp_api.domain.service.ProcessoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/processos")
public class ProcessoResource {

    private final ProcessoService processoService;
    private final ProcessoMapper mapper;

    @GetMapping
    public List<ProcessoModel> listar() {
        return mapper.toModelList(processoService.listar());
    }

    @GetMapping("/{processoId}")
    private ResponseEntity<ProcessoModel> buscar(@PathVariable String processoId) {
        Processo processo = processoService.buscar(processoId);

        if (processo != null) {
            ProcessoModel model = mapper.toModel(processo);
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProcessoModel> salvar(@RequestBody ProcessoInput input) {
        Processo processo = mapper.toEntity(input);
        ProcessoModel model = mapper.toModel(processoService.salvar(processo));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{processoId}")
    public ResponseEntity<ProcessoModel> atualizar(@PathVariable String processoId,
                                                   @RequestBody ProcessoInput input) {
        Processo processoAtual = mapper.toEntity(input);
        Processo processoAtualizado = processoService.atualizar(processoId, processoAtual);
        ProcessoModel model = mapper.toModel(processoAtualizado);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{processoId}")
    public ResponseEntity<ProcessoModel> remover(@PathVariable String processoId) {
        try {
            processoService.remover(processoId);
            return ResponseEntity.noContent().build();
        } catch (ProcessoNaoEcontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
