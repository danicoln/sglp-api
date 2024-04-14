package com.sglp.sglp_api.api.resource;

import com.sglp.sglp_api.api.assembler.ObjetoLaudoModelAssembler;
import com.sglp.sglp_api.api.disassembler.ObjetoLaudoInputDisassembler;
import com.sglp.sglp_api.api.dto.input.ObjetoLaudoInput;
import com.sglp.sglp_api.api.dto.model.ObjetoLaudoModel;
import com.sglp.sglp_api.domain.model.ObjetoLaudo;
import com.sglp.sglp_api.domain.service.ObjetoLaudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetos")
public class ObjetoLaudoResource {

    @Autowired
    private ObjetoLaudoService objetoLaudoService;

    @Autowired
    private ObjetoLaudoModelAssembler objetoLaudoModelAssembler;

    @Autowired
    private ObjetoLaudoInputDisassembler objetoLaudoInputDisassembler;

    @GetMapping
    public List<ObjetoLaudoModel> listar() {
        return objetoLaudoModelAssembler.toCollectionModel(objetoLaudoService.listar());
    }

    @GetMapping("/{objetoId}")
    public ResponseEntity<?> buscar(@PathVariable String objetoId) {
        ObjetoLaudo objetoLaudo = objetoLaudoService.buscar(objetoId);
        if(objetoLaudo != null) {
            ObjetoLaudoModel model = objetoLaudoModelAssembler.toModel(objetoLaudo);
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ObjetoLaudoModel> salvar(@RequestBody ObjetoLaudoInput input) {
        ObjetoLaudo objetoLaudo = objetoLaudoInputDisassembler.toDomainObject(input);
        ObjetoLaudoModel model =
                objetoLaudoModelAssembler.toModel(objetoLaudoService.salvar(objetoLaudo));

        return ResponseEntity.ok().body(model);
    }

    @PutMapping("/{objetoId}")
    public ObjetoLaudoModel atualizar(@PathVariable String objetoId,
                                      @RequestBody ObjetoLaudoInput input) {
        ObjetoLaudo objetoLaudoAtual = objetoLaudoService.buscarOuFalhar(objetoId);
        objetoLaudoInputDisassembler.copyToDomainObject(input, objetoLaudoAtual);

        ObjetoLaudo objeto = objetoLaudoService.salvar(objetoLaudoAtual);

        return objetoLaudoModelAssembler.toModel(objeto);
    }
    @DeleteMapping("/{objetoId}")
    public ResponseEntity<ObjetoLaudo> remover(@PathVariable String objetoId) {
        ObjetoLaudo objeto = objetoLaudoService.buscarOuFalhar(objetoId);
        if(objeto.getId().equals(objetoId)) {
            objetoLaudoService.remover(objetoId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
