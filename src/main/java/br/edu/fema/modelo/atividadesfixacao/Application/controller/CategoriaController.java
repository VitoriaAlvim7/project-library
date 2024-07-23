package br.edu.fema.modelo.atividadesfixacao.Application.controller;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.CategoriaDto;
import br.edu.fema.modelo.atividadesfixacao.Application.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Long id) {
        CategoriaDto categoriaDto = categoriaService.buscarPorId(id);
        return ResponseEntity.ok().body(categoriaDto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto) {
        CategoriaDto novaCategoria = this.categoriaService.atualizarCategoria(id, categoriaDto);
        return ResponseEntity.ok().body(novaCategoria);
    }

    @PostMapping("/criar/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<CategoriaDto> criarCategoria(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto) {
        CategoriaDto categoriaCriada = categoriaService.criarCategoria(categoriaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoriaCriada.getId()).toUri();
        return ResponseEntity.created(uri).body(categoriaCriada);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar-por-id/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        categoriaService.deletarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-primeiro-nome/{nome}")
    public ResponseEntity<CategoriaDto> buscarPrimeiroNome(@PathVariable String nome) {
        CategoriaDto categoriaDto = categoriaService.buscarPrimeiraCategoria(nome);
        return ResponseEntity.ok().body(categoriaDto);
    }

    @GetMapping("/ordenadas-desc")
    public ResponseEntity<List<CategoriaDto>> listarCategoriasDesc() {
        List<CategoriaDto> categoria = categoriaService.listarCategoriasOrdenadasDesc();
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/ordenadas-asc")
    public ResponseEntity<List<CategoriaDto>> listarCategoriasAsc() {
        List<CategoriaDto> categoria = categoriaService.listarCategoriasOrdenadasAsc();
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/buscar-por-ids")
    public ResponseEntity<List<CategoriaDto>> buscarCategoriasPorIds(@RequestParam List<Long> ids) {
        List<CategoriaDto> categoriaDtos = categoriaService.listarCategoriasPorIds(ids);
        return ResponseEntity.ok(categoriaDtos);
    }
}
