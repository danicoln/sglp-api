package com.sglp.sglp_api.api.controller;

import com.sglp.sglp_api.api.assembler.LaudoPericialModelAssembler;
import com.sglp.sglp_api.api.dto.input.LaudoPericialInput;
import com.sglp.sglp_api.api.disassembler.LaudoPericialInputDisassembler;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.service.LaudoPericialService;
import com.sglp.sglp_api.api.dto.model.LaudoPericialModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laudos")
public class LaudoPericialController {

    @Autowired
    private LaudoPericialService laudoPericialService;

    @Autowired
    private LaudoPericialModelAssembler laudoPericialModelAssembler;

    @Autowired
    private LaudoPericialInputDisassembler laudoPericialInputDisassembler;

    @GetMapping
    public List<LaudoPericialModel> listar() {
        return laudoPericialModelAssembler.toCollectionModel(laudoPericialService.listar());
    }

    @GetMapping("/{laudoId}")
    public LaudoPericialModel buscar(@PathVariable Long laudoId) {
        LaudoPericial laudoPericial = laudoPericialService.buscarOuFalhar(laudoId);

        return laudoPericialModelAssembler.toModel(laudoPericial);
    }

    @PostMapping
    public ResponseEntity<LaudoPericialModel> criar(@RequestBody @Valid LaudoPericialInput input) {
        LaudoPericial laudo = laudoPericialInputDisassembler.toDomainObject(input);

        LaudoPericialModel laudoPericial = laudoPericialModelAssembler.toModel(laudoPericialService.salvar(laudo));
        return ResponseEntity.ok().body(laudoPericial);
    }
}
