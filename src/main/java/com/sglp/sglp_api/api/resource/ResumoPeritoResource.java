package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.assembler.ResumoPeritoModelAssembler;
import com.sglp.sglp_api.api.disassembler.ResumoPeritoInputDisassembler;
import com.sglp.sglp_api.api.dto.input.ResumoPeritoInput;
import com.sglp.sglp_api.api.dto.model.ResumoPeritoModel;
import com.sglp.sglp_api.domain.model.ResumoPerito;
import com.sglp.sglp_api.domain.service.ResumoPeritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumo-perito")
public class ResumoPeritoResource {

    @Autowired
    private ResumoPeritoService peritoService;

    @Autowired
    private ResumoPeritoModelAssembler resumoPeritoModelAssembler;

    @Autowired
    private ResumoPeritoInputDisassembler resumoPeritoInputDisassembler;

    @PostMapping
    public ResponseEntity<ResumoPeritoModel> criar(@RequestBody ResumoPeritoInput input) {
       ResumoPerito perito = resumoPeritoInputDisassembler.toDomainObject(input);

       ResumoPeritoModel model = resumoPeritoModelAssembler.toModel(peritoService.criar(perito));
       return ResponseEntity.ok().body(model);
    }

    @GetMapping
    public List<ResumoPeritoModel> listar() {

        return resumoPeritoModelAssembler.toCollectionModel(peritoService.listar());
    }
}
