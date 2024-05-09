package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.LaudoPericialInput;
import com.sglp.sglp_api.api.dto.model.LaudoPericialModel;
import com.sglp.sglp_api.api.mapper.LaudoPericialMapper;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.service.LaudoPericialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laudos")
public class LaudoPericialResource {

    @Autowired
    private LaudoPericialService laudoPericialService;

    @Autowired
    private LaudoPericialMapper mapper;

    @GetMapping
    public List<LaudoPericialModel> listar() {
        return mapper.toModelList(laudoPericialService.listar());
    }

    @GetMapping("/{laudoId}")
    public ResponseEntity<LaudoPericialModel> buscar(@PathVariable String laudoId) {
        Optional<LaudoPericial> laudoPericial = laudoPericialService.buscar(laudoId);

        if (laudoPericial.isPresent()) {
            LaudoPericialModel model = mapper.toModel(laudoPericial.get());
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.notFound().build();
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

    //TODO: Implementar método de ativar e desativar laudo pericial.

}
