package br.edu.fema.modelo.atividadesfixacao.Application.controller;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.AutorDto;
import br.edu.fema.modelo.atividadesfixacao.Application.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AutorDto> buscarPorId(@PathVariable Long id) {
        AutorDto autorDto = this.autorService.buscarPorId(id);
        return ResponseEntity.ok().body(autorDto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AutorDto> atualizar(@PathVariable Long id, @RequestBody AutorDto autor) {
        AutorDto novoAutor = this.autorService.atualizarAutor(id, autor);
        return ResponseEntity.ok().body(novoAutor);
    }

    @PostMapping("/criar/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<AutorDto> criarAutor(@PathVariable Long id, @RequestBody AutorDto autor) {
        AutorDto autorCriado = autorService.criarAutor(autor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(autorCriado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        autorService.deletarAutor(id);
        return ResponseEntity.noContent().build();
    }

}
