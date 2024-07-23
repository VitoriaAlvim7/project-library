package br.edu.fema.modelo.atividadesfixacao.Application.dto;

import br.edu.fema.modelo.atividadesfixacao.Application.model.Emprestimo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class EmprestimoDto {

    private long id;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataRetorno;

    public EmprestimoDto(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataRetorno = emprestimo.getDataRetorno();
    }

    public static List<EmprestimoDto> converter(List<Emprestimo> listaEmprestimo) {
        return listaEmprestimo.stream().map(EmprestimoDto::new).collect(Collectors.toList());
    }
}
