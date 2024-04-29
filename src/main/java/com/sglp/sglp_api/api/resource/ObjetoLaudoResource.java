package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.ObjetoLaudoInput;
import com.sglp.sglp_api.api.dto.model.ObjetoLaudoModel;
import com.sglp.sglp_api.api.mapper.ObjetoLaudoMapper;
import com.sglp.sglp_api.domain.model.ExameDaMateria;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.service.ObjetoLaudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exames/{exameId}/objetos")
public class ObjetoLaudoResource {

    @Autowired
    private ObjetoLaudoService service;

    @Autowired
    private ObjetoLaudoMapper mapper;

    @PostMapping
    public ResponseEntity<ObjetoLaudoModel> salvar(@PathVariable String exameId,
                                                   @RequestBody ObjetoLaudoInput input) {

        ObjetoLaudo objetoLaudo = mapper.toEntity(input);
        ObjetoLaudo objSalvo = service.salvar(exameId, objetoLaudo);
        ObjetoLaudoModel model = mapper.toModel(objSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @GetMapping
    public ResponseEntity<List<ObjetoLaudoModel>> listar(@PathVariable String exameId) {
        Optional<ExameDaMateria> exameDaMateria = service.buscarExame(exameId);

        if (exameDaMateria.isEmpty() || exameDaMateria == null) {
            return ResponseEntity.notFound().build();
        }
        List<ObjetoLaudo> objetos = service.listar();
        List<ObjetoLaudoModel> models = mapper.toModelList(objetos);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/{objetoId}")
    public ResponseEntity<?> buscarPorId(@PathVariable String exameId,
                                         @PathVariable String objetoId) {
        final var objetoLaudo = service.buscarPorId(exameId, objetoId);
        return ResponseEntity.ok(mapper.toModel(objetoLaudo));
    }

    @PutMapping("/{objetoId}")
    public ResponseEntity<ObjetoLaudoModel> atualizar(@PathVariable String exameId, @PathVariable String objetoId,
                                                      @RequestBody ObjetoLaudoInput input) {
        ObjetoLaudo objetoLaudo = mapper.toEntity(input);
        ObjetoLaudo objSalvo = service.atualizar(exameId, objetoId, objetoLaudo);
        ObjetoLaudoModel model = mapper.toModel(objSalvo);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{objetoId}")
    public ResponseEntity<?> remover(@PathVariable String exameId, @PathVariable String objetoId) {
        Optional<ExameDaMateria> exameDaMateria = service.buscarExame(exameId);

        if (exameDaMateria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ObjetoLaudo objetoLaudo = service.buscarPorId(exameId, objetoId);
        if (objetoLaudo == null) {
            return ResponseEntity.notFound().build();
        }
        service.remover(objetoId);
        return ResponseEntity.noContent().build();
    }
}
