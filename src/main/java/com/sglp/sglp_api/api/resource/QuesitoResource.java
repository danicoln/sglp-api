package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.assembler.QuesitoModelAssembler;
import com.sglp.sglp_api.api.disassembler.QuesitoInputDisassembler;
import com.sglp.sglp_api.api.dto.input.QuesitoInput;
import com.sglp.sglp_api.api.dto.model.QuesitoModel;
import com.sglp.sglp_api.domain.model.Quesito;
import com.sglp.sglp_api.domain.service.QuesitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quesitos")
public class QuesitoResource {

    @Autowired
    private QuesitoService quesitoService;

    @Autowired
    private QuesitoModelAssembler quesitoModelAssembler;

    @Autowired
    private QuesitoInputDisassembler quesitoInputDisassembler;

    @GetMapping
    public List<QuesitoModel> listar() {
        return quesitoModelAssembler.toCollectionModel(quesitoService.listar());
    }

    @GetMapping("/{quesitoId}")
    public QuesitoModel buscar(@PathVariable String quesitoId) {
        Quesito quesito = quesitoService.buscarOuFalhar(quesitoId);

        return quesitoModelAssembler.toModel(quesito);
    }

    @PostMapping
    public ResponseEntity<QuesitoModel> salvar(@RequestBody QuesitoInput input) {
        Quesito quesito = quesitoInputDisassembler.toDomainObject(input);
        QuesitoModel model = quesitoModelAssembler.toModel(quesitoService.salvar(quesito));

        return ResponseEntity.ok().body(model);
    }

    @PutMapping("/{quesitoId}")
    public QuesitoModel atualizar(@PathVariable String quesitoId, @RequestBody QuesitoInput input) {
        Quesito quesitoAtual = quesitoService.buscarOuFalhar(quesitoId);
        quesitoInputDisassembler.copyToDomainObject(input, quesitoAtual);

        Quesito quesito = quesitoService.salvar(quesitoAtual);

        return quesitoModelAssembler.toModel(quesito);
    }
}
