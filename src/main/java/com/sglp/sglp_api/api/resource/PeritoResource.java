package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.dto.input.PeritoInput;
import com.sglp.sglp_api.api.dto.model.PeritoModel;
import com.sglp.sglp_api.api.mapper.PeritoMapper;
import com.sglp.sglp_api.domain.model.Perito;
import com.sglp.sglp_api.domain.service.PeritoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/peritos")
public class PeritoResource {

    private final PeritoService peritoService;
    private final PeritoMapper mapper;

    @PostMapping
    public ResponseEntity<PeritoModel> criar(@RequestBody PeritoInput input) {
        Perito perito = mapper.toEntity(input);
        PeritoModel model = mapper.toModel(peritoService.criar(perito));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @GetMapping
    public List<PeritoModel> listar() {
        return mapper.toModelList(peritoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeritoModel> buscarPorId(@PathVariable String id) {
        Optional<Perito> perito = peritoService.buscarPorId(id);

        if (perito.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PeritoModel model = mapper.toModel(perito.get());
        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeritoModel> atualizar(@PathVariable String id,
                                                 @RequestBody PeritoInput input) {
        Perito perito = mapper.toEntity(input);
        Perito peritoAtualizado = peritoService.atualizar(id, perito);
        PeritoModel model = mapper.toModel(peritoAtualizado);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable String id) {
        Optional<Perito> peritoOptional = peritoService.buscarPorId(id);

        if (peritoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Perito perito = peritoOptional.get();
        perito.setAtivo(true);
        peritoService.atualizar(id, perito);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable String id) {
        Optional<Perito> peritoOptional = peritoService.buscarPorId(id);

        if (peritoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Perito perito = peritoOptional.get();
        perito.setAtivo(false);
        peritoService.atualizar(id, perito);

        return ResponseEntity.noContent().build();
    }
}
