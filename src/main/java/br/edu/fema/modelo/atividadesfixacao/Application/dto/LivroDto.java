package br.edu.fema.modelo.atividadesfixacao.Application.dto;

import br.edu.fema.modelo.atividadesfixacao.Application.model.Livro;
import br.edu.fema.modelo.atividadesfixacao.Application.service.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {

    private long id;
    private String titulo;
    private BigDecimal preco;
    private LocalDate dataPublicacao;
    private Situacao situacao;
    private EmprestimoDto emprestimoDto;
    private AutorDto autorDto;

    private List<CategoriaDto> categorias;

    public LivroDto(Livro livro, List<CategoriaDto> categorias, EmprestimoDto emprestimoDto) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.preco = livro.getPreco();
        this.dataPublicacao = livro.getDataPublicacao();
        this.situacao = livro.getSituacao();
        this.autorDto = livro.getAutor() != null ? new AutorDto(livro.getAutor()) : null;
        this.emprestimoDto = livro.getEmprestimo() != null ? new EmprestimoDto(livro.getEmprestimo()) : null;
        this.categorias = categorias != null ? categorias : livro.getCategorias()
                .stream().map(CategoriaDto::new).collect(Collectors.toList());new ArrayList<>();
    }

    public static List<LivroDto> converter(List<Livro> listaLivros) {
        return listaLivros.stream().map(livro -> new LivroDto(livro, null, null)).collect(Collectors.toList());
    }
}
