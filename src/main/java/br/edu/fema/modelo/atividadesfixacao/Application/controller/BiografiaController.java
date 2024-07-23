package br.edu.fema.modelo.atividadesfixacao.Application.controller;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.BiografiaDto;
import br.edu.fema.modelo.atividadesfixacao.Application.model.BiografiaId;
import br.edu.fema.modelo.atividadesfixacao.Application.service.BiografiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/biografia")
public class BiografiaController {

    @Autowired
    private BiografiaService biografiaService;

    @GetMapping("/buscar/{autorId}/{biografiaId}")
    public ResponseEntity<BiografiaDto> buscarPorId(@PathVariable Long autorId, @PathVariable Long biografiaId) {
        BiografiaId id = new BiografiaId();
        id.setAutorId(autorId);
        id.setBiografiaId(biografiaId);
        BiografiaDto biografiaDto = biografiaService.buscarPorId(id);
        return ResponseEntity.ok().body(biografiaDto);
    }

    @PutMapping("/atualizar/{autorId}/{biografiaId}")
    public ResponseEntity<BiografiaDto> atualizar (@PathVariable Long autorId, @PathVariable Long biografiaId, @RequestBody BiografiaDto biografiaDto) {
        BiografiaId id = new BiografiaId();
        id.setAutorId(autorId);
        id.setBiografiaId(biografiaId);

        BiografiaDto biografiaAtualizada = biografiaService.atualizarBiografia(id, biografiaDto);
        return ResponseEntity.ok().body(biografiaAtualizada);
    }

    @PostMapping("/criar/{autorId}/{biografiaId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<BiografiaDto> criarBiografia(@PathVariable Long autorId, @PathVariable Long biografiaId, @RequestBody BiografiaDto biografiaDto) {

        BiografiaId id = new BiografiaId();
        id.setAutorId(autorId);
        id.setBiografiaId(biografiaId);

        BiografiaDto biografiaCriada = biografiaService.criarBiografia(id, biografiaDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{autorId}/{biografiaId}").buildAndExpand(autorId, biografiaId).toUri();
        return ResponseEntity.created(uri).body(biografiaCriada);
    }

    @DeleteMapping("/deletar/{autorId}/{biografiaId}")
    public ResponseEntity<Void> deletarBiografia(@PathVariable Long autorId, @PathVariable Long biografiaId) {

        BiografiaId id = new BiografiaId();
        id.setAutorId(autorId);
        id.setBiografiaId(biografiaId);

        return ResponseEntity.noContent().build();
    }

}
