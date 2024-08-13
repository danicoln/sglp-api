package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.ChatGPTRequest;
import com.sglp.sglp_api.api.dto.input.QuesitoInput;
import com.sglp.sglp_api.api.dto.model.QuesitoModel;
import com.sglp.sglp_api.api.mapper.QuesitoMapper;
import com.sglp.sglp_api.domain.exception.EntidadeNaoEncontradaException;
import com.sglp.sglp_api.domain.model.Quesito;
import com.sglp.sglp_api.domain.service.IAService;
import com.sglp.sglp_api.domain.service.QuesitoService;
import com.sglp.sglp_api.domain.service.strategy.QuesitoStrategy;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/laudos/{laudoId}/quesitos")
public class QuesitoResource {

    private final QuesitoService quesitoService;
    private final QuesitoStrategy quesitoStrategy;
    private final QuesitoMapper mapper;
    private final IAService iaService;

    @GetMapping
    public ResponseEntity<List<QuesitoModel>> listar(@PathVariable String laudoId) {
        List<Quesito> quesitos = quesitoService.listar(laudoId);
        return ResponseEntity.ok(mapper.toModelList(quesitos));
    }

    @GetMapping("/{quesitoId}")
    public ResponseEntity<QuesitoModel> buscar(@PathVariable String quesitoId) {
        Quesito quesito = quesitoService.buscarPorIdOuFalhar(quesitoId);

        return ResponseEntity.ok(mapper.toModel(quesito));
    }

    @PostMapping
    public ResponseEntity<QuesitoModel> salvar(@PathVariable String laudoId,
                                               @RequestBody QuesitoInput input) {
        Quesito quesito = mapper.toEntity(input);
        QuesitoModel model = mapper.toModel(quesitoService.salvar(laudoId, quesito));

        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{quesitoId}")
    public ResponseEntity<QuesitoModel> atualizar(@PathVariable String quesitoId,
                                                  @RequestBody QuesitoInput input) {
        Quesito quesito = mapper.toEntity(input);
        Quesito quesitoAtualizado = quesitoService.atualizar(quesitoId, quesito);
        QuesitoModel model = mapper.toModel(quesitoAtualizado);

        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{quesitoId}")
    public ResponseEntity<QuesitoModel> remover(@PathVariable String quesitoId) {
        try {
            quesitoService.remover(quesitoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ia")
    public ResponseEntity<Map<String, String>> getIAResponse(@RequestBody ChatGPTRequest request){
        var prompt = quesitoStrategy.buildPrompt(request);
        var response = iaService.processEntityRequest(request, prompt);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("content", response);
        return ResponseEntity.ok(responseBody);
    }

}
