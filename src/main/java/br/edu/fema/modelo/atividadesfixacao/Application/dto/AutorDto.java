package br.edu.fema.modelo.atividadesfixacao.Application.dto;

import br.edu.fema.modelo.atividadesfixacao.Application.model.Autor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AutorDto {

    private long id;
    private String nome;
    private BiografiaDto biografiaDto;

    public AutorDto(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.biografiaDto = autor.getBiografia() != null ? new BiografiaDto(autor.getBiografia()):null;
    }

    public static List<AutorDto> converter(List<Autor> listaAutor) {
        return listaAutor.stream().map(AutorDto::new).collect(Collectors.toList());
    }
}
