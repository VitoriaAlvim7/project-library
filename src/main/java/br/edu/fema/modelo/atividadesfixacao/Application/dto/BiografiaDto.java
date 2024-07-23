package br.edu.fema.modelo.atividadesfixacao.Application.dto;

import br.edu.fema.modelo.atividadesfixacao.Application.model.Biografia;
import br.edu.fema.modelo.atividadesfixacao.Application.model.BiografiaId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BiografiaDto {

    private Long autorId;
    private Long biografiaId;
    private String detalhes;

    public BiografiaDto(Biografia biografia) {
        if (biografia.getId() != null) {
            this.autorId = biografia.getId().getAutorId();
            this.biografiaId = biografia.getId().getBiografiaId();
        }
        this.detalhes = biografia.getDetalhes();
    }

    public Biografia toEntity() {
        Biografia biografia = new Biografia();
        biografia.setId(new BiografiaId(this.autorId, this.biografiaId));
        biografia.setDetalhes(this.detalhes);
        return biografia;
    }

    public static List<BiografiaDto> converter(List<Biografia>listaBiografia) {
        return listaBiografia.stream().map(BiografiaDto::new).collect(Collectors.toList());
    }
}
