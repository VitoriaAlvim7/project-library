package br.edu.fema.modelo.atividadesfixacao.Application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class BiografiaId implements Serializable {

    @Column(name = "autor_id")
    private long autorId;

    private long biografiaId;

}

