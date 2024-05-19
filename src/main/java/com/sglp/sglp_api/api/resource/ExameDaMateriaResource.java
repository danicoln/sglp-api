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
@RequestMapping("/api/laudos/{laudoId}/exames")
public class ExameDaMateriaResource {

    @Autowired
    private ExameDaMateriaService service;

    @Autowired
    private ExameDaMateriaMapper mapper;

    //TODO: Se um laudo pericial contém apenas um único exame, não é necessário um método de listagem de exames ?!
//    @GetMapping
//    public ResponseEntity<ExameDaMateriaModel> listar(@PathVariable String laudoId) {
//        ExameDaMateria exameDaMateria = service.listar(laudoId);
//        if(exameDaMateria == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(mapper.toModel(exameDaMateria));
//    }

    @GetMapping("/{exameId}")
    public ResponseEntity<?> buscarPoId(@PathVariable String laudoId,
                                        @PathVariable String exameId) {
        final var exameDaMateria = service.buscarPorId(laudoId, exameId);

        if (exameDaMateria != null) {
            ExameDaMateriaModel model = mapper.toModel(exameDaMateria);
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExameDaMateriaModel> salvar(@PathVariable String laudoId,
                                                      @RequestBody ExameDaMateriaInput input) {
        ExameDaMateria exameDaMateria = mapper.toEntity(input);
        ExameDaMateriaModel model = mapper.toModel(service.salvar(laudoId, exameDaMateria));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{exameId}")
    public ResponseEntity<ExameDaMateriaModel> atualizar(@PathVariable String laudoId,
                                                         @PathVariable String exameId,
                                                         @RequestBody ExameDaMateriaInput input) {
        ExameDaMateria exame = mapper.toEntity(input);
        ExameDaMateria exameAtualizado = service.atualizar(laudoId, exameId, exame);
        ExameDaMateriaModel model = mapper.toModel(exameAtualizado);

        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{exameId}")
    public ResponseEntity<ExameDaMateria> remover(@PathVariable String laudoId,
                                                  @PathVariable String exameId) {
        ExameDaMateria exame = service.buscarOuFalhar(exameId);
        if (exame.getId().equals(exameId)) {
            service.remover(exameId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
