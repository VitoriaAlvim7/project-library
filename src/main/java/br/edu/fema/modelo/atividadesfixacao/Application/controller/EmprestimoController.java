package br.edu.fema.modelo.atividadesfixacao.Application.controller;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.EmprestimoDto;
import br.edu.fema.modelo.atividadesfixacao.Application.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<EmprestimoDto> buscarPorId(@PathVariable Long id) {
        EmprestimoDto emprestimoDto = emprestimoService.buscarPorId(id);
        return ResponseEntity.ok().body(emprestimoDto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EmprestimoDto> atualizar(@PathVariable Long id, @RequestBody EmprestimoDto emprestimoDto) {
        EmprestimoDto novoEmprestimo = this.emprestimoService.atualizarEmprestimo(id, emprestimoDto);
        return ResponseEntity.ok().body(novoEmprestimo);
    }

    @PostMapping("/criar/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<EmprestimoDto> criarEmprestimo(@PathVariable Long id, @RequestBody EmprestimoDto emprestimoDto) {
        EmprestimoDto emprestimoCriado = emprestimoService.criarEmprestimo(emprestimoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emprestimoCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(emprestimoCriado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        emprestimoService.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/entre-datas")
    public ResponseEntity<List<EmprestimoDto>> buscarEntreDatas(@RequestParam String dataEmprestimo, @RequestParam String dataRetorno) {
        List<EmprestimoDto> entreDatas = this.emprestimoService.buscarEntreDatas(dataEmprestimo, dataRetorno);
        return ResponseEntity.ok(entreDatas);
    }

    @GetMapping("/data-emprestimo/{dataEmprestimo}")
    public ResponseEntity<List<EmprestimoDto>> buscarData(@PathVariable String dataEmprestimo) {
        LocalDateTime data = LocalDateTime.parse(dataEmprestimo, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<EmprestimoDto> dataEmprestimoDto = emprestimoService.buscarPorData(data);
        return ResponseEntity.ok(dataEmprestimoDto);
    }

    @GetMapping("/data-igual/{dataEmprestimo}")
    public ResponseEntity<List<EmprestimoDto>> buscarDataIgual(@PathVariable String dataEmprestimo) {
        LocalDateTime data = LocalDateTime.parse(dataEmprestimo, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<EmprestimoDto> dataEmprestimoDto = emprestimoService.buscarPorDataIgual(data);
        return ResponseEntity.ok(dataEmprestimoDto);
    }

    @GetMapping("/data-retorno/{dataRetorno}")
    public ResponseEntity<List<EmprestimoDto>> buscarRetorno(@PathVariable String dataRetorno) {
        LocalDateTime data = LocalDateTime.parse(dataRetorno, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<EmprestimoDto> dataEmprestimoDto = emprestimoService.buscarMenorData(data);
        return ResponseEntity.ok(dataEmprestimoDto);
    }

    @GetMapping("/data-retorno-igual/{dataRetorno}")
    public ResponseEntity<List<EmprestimoDto>> buscarRetornoIgual(@PathVariable String dataRetorno) {
        LocalDateTime data = LocalDateTime.parse(dataRetorno, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<EmprestimoDto> dataEmprestimoDto = emprestimoService.buscarMenorDataIgual(data);
        return ResponseEntity.ok(dataEmprestimoDto);
    }

}

