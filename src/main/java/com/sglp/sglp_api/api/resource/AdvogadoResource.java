package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.AdvogadoInput;
import com.sglp.sglp_api.api.dto.model.AdvogadoModel;
import com.sglp.sglp_api.api.mapper.AdvogadoMapper;
import com.sglp.sglp_api.domain.exception.AdvogadoNaoEncontradoException;
import com.sglp.sglp_api.domain.model.Advogado;
import com.sglp.sglp_api.domain.service.AdvogadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/advogados")
public class AdvogadoResource {

    private final AdvogadoService service;
    private final AdvogadoMapper mapper;

    @GetMapping
    public ResponseEntity<List<AdvogadoModel>> listar() {
        List<Advogado> advogados = service.listar();
        return ResponseEntity.ok(mapper.toModelList(advogados));
    }

    @GetMapping("/{advogadoId}")
    public ResponseEntity<AdvogadoModel> buscarPorId(@PathVariable String advogadoId) {
        Advogado advogado = service.buscarPorIdOuFalhar(advogadoId);
        return ResponseEntity.ok(mapper.toModel(advogado));
    }

    @PostMapping
    public ResponseEntity<AdvogadoModel> salvar(@RequestBody AdvogadoInput input) {
        Advogado advogado = mapper.toEntity(input);
        AdvogadoModel model = mapper.toModel(service.salvar(advogado));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{advogadoId}")
    public ResponseEntity<AdvogadoModel> atualizar(@PathVariable String advogadoId,
                                                   @RequestBody AdvogadoInput input) {
        Advogado advogadoAtual = mapper.toEntity(input);
        Advogado advogadoAtualizado = service.atualizar(advogadoId, advogadoAtual);
        return ResponseEntity.ok(mapper.toModel(advogadoAtualizado));
    }

    @DeleteMapping("/{advogadoId}")
    public ResponseEntity<AdvogadoModel> remover(@PathVariable String advogadoId) {
        try {
            service.remover(advogadoId);
            return ResponseEntity.noContent().build();
        } catch (AdvogadoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
