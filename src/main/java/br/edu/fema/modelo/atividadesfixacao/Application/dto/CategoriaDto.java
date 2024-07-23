package br.edu.fema.modelo.atividadesfixacao.Application.dto;

import br.edu.fema.modelo.atividadesfixacao.Application.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {

    private long id;
    private String nome;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public static List<CategoriaDto> converter(List<Categoria> listaCategoria) {
        return listaCategoria.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }
}
