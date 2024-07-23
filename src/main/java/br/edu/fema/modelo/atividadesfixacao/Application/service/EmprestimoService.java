package br.edu.fema.modelo.atividadesfixacao.Application.service;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.EmprestimoDto;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Emprestimo;
import br.edu.fema.modelo.atividadesfixacao.Application.repository.EmprestimoRepository;
import br.edu.fema.modelo.atividadesfixacao.Application.exceptions.custom.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoDto buscarPorId(Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        Emprestimo buscaEmprestimo = emprestimo.orElseThrow(() -> new ObjectNotFoundException("Emprestimo não encontrado"));
        return new EmprestimoDto(buscaEmprestimo);
    }

    public EmprestimoDto atualizarEmprestimo(Long id, EmprestimoDto emprestimoDto) {
        Emprestimo emprestimoAntigo = emprestimoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Emprestimo não encontrado"));

        emprestimoAntigo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());
        emprestimoAntigo.setDataRetorno(emprestimoDto.getDataRetorno());

        Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimoAntigo);
        return new EmprestimoDto(emprestimoAtualizado);
    }

    public EmprestimoDto criarEmprestimo(EmprestimoDto emprestimoDto) {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());

        emprestimo = emprestimoRepository.save(emprestimo);
        return emprestimoDto;
    }

    public void deletarEmprestimo(Long id) {
        buscarPorId(id);
        emprestimoRepository.deleteById(id);
    }

    public List<EmprestimoDto> buscarPorDataIgual(LocalDateTime dataEmprestimo) {
        List<Emprestimo> emprestimo = this.emprestimoRepository.findEmprestimoByDataEmprestimoGreaterThanEqual(dataEmprestimo);
        return EmprestimoDto.converter(emprestimo);
    }

    public List<EmprestimoDto> buscarPorData(LocalDateTime dataEmprestimo) {
        List<Emprestimo> emprestimo = this.emprestimoRepository.findEmprestimoByDataEmprestimoGreaterThan(dataEmprestimo);
        return EmprestimoDto.converter(emprestimo);
    }

    public List<EmprestimoDto> buscarMenorDataIgual(LocalDateTime dataRetorno) {
        List<Emprestimo> emprestimo = this.emprestimoRepository.findEmprestimoByDataEmprestimoLessThanEqual(dataRetorno);
        return EmprestimoDto.converter(emprestimo);
    }

    public List<EmprestimoDto> buscarMenorData(LocalDateTime dataRetorno) {
        List<Emprestimo> emprestimo = this.emprestimoRepository.findEmprestimoByDataEmprestimoLessThan(dataRetorno);
        return EmprestimoDto.converter(emprestimo);
    }

    public List<EmprestimoDto> buscarEntreDatas(String dataEmprestimo, String dataRetorno) {
        LocalDateTime dataEmprestimoInicio = LocalDateTime.parse(dataEmprestimo, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime dataRetornoFinal = LocalDateTime.parse(dataRetorno, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<Emprestimo> listaDataEmprestimo = this.emprestimoRepository.findEmprestimoByDataEmprestimoBetween(dataEmprestimoInicio, dataRetornoFinal);
        return EmprestimoDto.converter(listaDataEmprestimo);
    }

}
