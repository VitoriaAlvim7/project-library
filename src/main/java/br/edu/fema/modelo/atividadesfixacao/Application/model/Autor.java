package br.edu.fema.modelo.atividadesfixacao.Application.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @OneToOne(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Biografia biografia;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Livro> livros;
}
