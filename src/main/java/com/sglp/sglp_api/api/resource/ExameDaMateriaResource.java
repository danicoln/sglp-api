package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.ExameDaMateriaInput;
import com.sglp.sglp_api.api.dto.model.ExameDaMateriaModel;
import com.sglp.sglp_api.api.mapper.ExameDaMateriaMapper;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.service.ExameDaMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exames")
public class ExameDaMateriaResource {

    @Autowired
    private ExameDaMateriaService exameDaMateriaService;

    @Autowired
    private ExameDaMateriaMapper mapper;

    @GetMapping
    public List<ExameDaMateriaModel> listar() {
        return mapper.toModelList(exameDaMateriaService.listar());
    }

    @GetMapping("/{exameId}")
    public ResponseEntity<ExameDaMateriaModel> buscar(@PathVariable String exameId) {
        ExameDaMateria exameDaMateria = exameDaMateriaService.buscar(exameId);

        if (exameDaMateria != null) {
            ExameDaMateriaModel model = mapper.toModel(exameDaMateria);
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExameDaMateriaModel> salvar(@RequestBody ExameDaMateriaInput input) {
        ExameDaMateria exameDaMateria = mapper.toEntity(input);
        ExameDaMateriaModel model = mapper.toModel(exameDaMateriaService.salvar(exameDaMateria));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{exameId}")
    public ResponseEntity<ExameDaMateriaModel> atualizar(@PathVariable String exameId,
                                                         @RequestBody ExameDaMateriaInput input) {
        ExameDaMateria exame = mapper.toEntity(input);
        ExameDaMateria exameAtualizado = exameDaMateriaService.atualizar(exameId, exame);
        ExameDaMateriaModel model = mapper.toModel(exameAtualizado);

        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{exameId}")
    public ResponseEntity<ExameDaMateria> remover(@PathVariable String exameId) {
        ExameDaMateria exame = exameDaMateriaService.buscarOuFalhar(exameId);
        if (exame.getId().equals(exameId)) {
            exameDaMateriaService.remover(exameId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
