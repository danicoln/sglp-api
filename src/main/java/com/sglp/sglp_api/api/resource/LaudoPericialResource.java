package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.ChatGPTRequest;
import com.sglp.sglp_api.api.dto.input.LaudoPericialInput;
import com.sglp.sglp_api.api.dto.model.LaudoPericialModel;
import com.sglp.sglp_api.api.mapper.LaudoPericialMapper;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.service.IAService;
import com.sglp.sglp_api.domain.service.LaudoPericialService;
import com.sglp.sglp_api.domain.service.strategy.LaudoStrategy;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/laudos")
public class LaudoPericialResource {

    private final LaudoPericialService laudoPericialService;
    private final LaudoPericialMapper mapper;
    private final IAService iaService;
    private final LaudoStrategy strategy;


    @GetMapping
    public List<LaudoPericialModel> listar(@RequestParam(value = "ativo", defaultValue = "true") boolean ativo) {
        return mapper.toModelList(laudoPericialService.listarAtivos(ativo));
    }

    @GetMapping("/{laudoId}")
    public ResponseEntity<LaudoPericialModel> buscar(@PathVariable String laudoId) {
        Optional<LaudoPericial> laudoPericial = laudoPericialService.buscar(laudoId);

        if (laudoPericial.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        LaudoPericialModel model = mapper.toModel(laudoPericial.get());
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<LaudoPericialModel> criar(@RequestBody @Valid LaudoPericialInput input) {
        LaudoPericial laudo = mapper.toEntity(input);
        LaudoPericialModel model = mapper.toModel(laudoPericialService.salvar(laudo));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{laudoId}")
    public ResponseEntity<LaudoPericialModel> atualizar(@PathVariable String laudoId,
                                                        @RequestBody LaudoPericialInput input) {
        LaudoPericial laudo = mapper.toEntity(input);
        LaudoPericial laudoAtualizado = laudoPericialService.atualizar(laudoId, laudo);
        LaudoPericialModel model = mapper.toModel(laudoAtualizado);

        return ResponseEntity.ok(model);
    }

    @PutMapping("/{laudoId}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable String laudoId) {
        Optional<LaudoPericial> laudoOpt = laudoPericialService.buscar(laudoId);

        if (laudoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        LaudoPericial laudo = laudoOpt.get();
        laudo.setAtivo(true);
        laudoPericialService.ativar(laudoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{laudoId}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable String laudoId) {
        Optional<LaudoPericial> laudoOpt = laudoPericialService.buscar(laudoId);

        if (laudoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        LaudoPericial laudo = laudoOpt.get();
        laudo.setAtivo(false);
        laudoPericialService.desativar(laudoId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ia")
    public ResponseEntity<Map<String, String>> getIAResponse(@RequestBody ChatGPTRequest request){
        var prompt = strategy.buildPrompt(request);
        var response = iaService.processEntityRequest(request, prompt);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("content", response);
        return ResponseEntity.ok(responseBody);
    }

}
