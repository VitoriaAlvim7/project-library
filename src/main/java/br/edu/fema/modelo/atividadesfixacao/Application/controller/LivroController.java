package br.edu.fema.modelo.atividadesfixacao.Application.controller;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.LivroDto;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Livro;
import br.edu.fema.modelo.atividadesfixacao.Application.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/lista")
    public ResponseEntity<List<LivroDto>> buscar() {
        return ResponseEntity.ok(this.livroService.buscarLivro());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroDto>> buscarPorTitulo(@PathVariable String titulo) {
        List<Livro> livroTitulo = this.livroService.buscarPorTitulo(titulo);
        List<LivroDto> livroDtoTitulo = LivroDto.converter(livroTitulo);
        return ResponseEntity.ok(livroDtoTitulo);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable Long id) {
        LivroDto livro = this.livroService.buscarPorId(id);
        return ResponseEntity.ok().body(livro);
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<LivroDto> atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        LivroDto novoLivro = livroService.atualizarLivro(id, livro);
        return ResponseEntity.ok().body(novoLivro);
    }

    @PostMapping("/criar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<LivroDto> criar(@RequestBody LivroDto livroDto) {
        LivroDto livroCriado = livroService.criarLivro(livroDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(livroCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(livroCriado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletarLivroPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-por-titulo-e-preco")
    public ResponseEntity<List<LivroDto>> buscarPorTituloEPreco(@RequestParam String titulo, @RequestParam BigDecimal preco) {

        List<LivroDto> livroDto = livroService.buscarPorTituloEPreco(titulo, preco);
        return ResponseEntity.ok().body(livroDto);
    }

    @GetMapping("/buscar-por-titulo-ou-preco")
    public ResponseEntity<List<LivroDto>> buscarPorTituloOuPreco(@RequestParam String titulo, @RequestParam BigDecimal preco) {

        List<LivroDto> livroDto = livroService.buscarPorTituloOuPreco(titulo, preco);
        return ResponseEntity.ok().body(livroDto);
    }

    @GetMapping("buscar-livro-disponivel")
    public ResponseEntity<List<LivroDto>> buscarLivroDisponivel() {
        List<LivroDto> livro = livroService.buscarLivroDisponivel();
        return ResponseEntity.ok().body(livro);
    }
}
