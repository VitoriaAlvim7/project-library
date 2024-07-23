package br.edu.fema.modelo.atividadesfixacao.Application.repository;

import br.edu.fema.modelo.atividadesfixacao.Application.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findEmprestimoByDataEmprestimoGreaterThanEqual(LocalDateTime dataEmprestimo);

    List<Emprestimo> findEmprestimoByDataEmprestimoGreaterThan(LocalDateTime dataEmprestimo);

    List<Emprestimo> findEmprestimoByDataEmprestimoLessThan(LocalDateTime dataRetorno);

    List<Emprestimo> findEmprestimoByDataEmprestimoLessThanEqual(LocalDateTime dataRetorno);

    List<Emprestimo> findEmprestimoByDataEmprestimoBetween(LocalDateTime dataEmprestimo, LocalDateTime dataRetorno);

}
