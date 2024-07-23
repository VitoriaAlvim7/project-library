package br.edu.fema.modelo.atividadesfixacao.Application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "biografia")
public class Biografia {

    @EmbeddedId
    private BiografiaId id;

    private String detalhes;

    @OneToOne
    @MapsId("autorId")
    private Autor autor;

}

