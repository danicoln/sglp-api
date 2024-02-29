package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.assembler.PeritoModelAssembler;
import com.sglp.sglp_api.api.disassembler.PeritoInputDisassembler;
import com.sglp.sglp_api.api.dto.input.PeritoInput;
import com.sglp.sglp_api.api.dto.model.PeritoModel;
import com.sglp.sglp_api.domain.model.Perito;
import com.sglp.sglp_api.domain.service.PeritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peritos")
public class PeritoResource {

    @Autowired
    private PeritoService peritoService;

    @Autowired
    private PeritoModelAssembler peritoModelAssembler;

    @Autowired
    private PeritoInputDisassembler peritoInputDisassembler;

    @PostMapping
    public ResponseEntity<PeritoModel> criar(@RequestBody PeritoInput input) {
        Perito perito = peritoInputDisassembler.toDomainObject(input);

        PeritoModel model = peritoModelAssembler.toModel(peritoService.criar(perito));
        return ResponseEntity.ok().body(model);
    }

    @GetMapping
    public List<PeritoModel> listar() {
        return peritoModelAssembler.toCollectionModel(peritoService.listar());
    }
}
