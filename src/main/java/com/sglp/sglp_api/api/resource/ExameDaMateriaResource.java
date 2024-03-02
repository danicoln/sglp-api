package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.assembler.ExameDaMateriaModelAssembler;
import com.sglp.sglp_api.api.disassembler.ExameDaMateriaInputDisassembler;
import com.sglp.sglp_api.api.dto.input.ExameDaMateriaInput;
import com.sglp.sglp_api.api.dto.model.ExameDaMateriaModel;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.service.ExameDaMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exames")
public class ExameDaMateriaResource {

    @Autowired
    private ExameDaMateriaService exameDaMateriaService;

    @Autowired
    private ExameDaMateriaModelAssembler exameDaMateriaModelAssembler;

    @Autowired
    private ExameDaMateriaInputDisassembler exameDaMateriaInputDisassembler;

    @GetMapping
    public List<ExameDaMateriaModel> listar() {
        return exameDaMateriaModelAssembler.toCollectionModel(exameDaMateriaService.listar());
    }

    @GetMapping("/{exameId}")
    public ResponseEntity<ExameDaMateriaModel> buscar(@PathVariable String exameId) {
        ExameDaMateria exameDaMateria = exameDaMateriaService.buscar(exameId);

        if(exameDaMateria != null) {
            ExameDaMateriaModel model = exameDaMateriaModelAssembler.toModel(exameDaMateria);
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExameDaMateriaModel> salvar(@RequestBody ExameDaMateriaInput input) {
        ExameDaMateria exameDaMateria = exameDaMateriaInputDisassembler.toDomainObject(input);
        ExameDaMateriaModel model =
                exameDaMateriaModelAssembler.toModel(exameDaMateriaService.salvar(exameDaMateria));
        return ResponseEntity.ok().body(model);
    }

    @PutMapping("/{exameId}")
    public ExameDaMateriaModel atualizar(@PathVariable String exameId,
                                         @RequestBody ExameDaMateriaInput input) {
        ExameDaMateria exameAtual = exameDaMateriaService.buscarOuFalhar(exameId);
        exameDaMateriaInputDisassembler.copyToDomainObject(input, exameAtual);

        ExameDaMateria exame = exameDaMateriaService.salvar(exameAtual);

        return exameDaMateriaModelAssembler.toModel(exame);
    }
}
