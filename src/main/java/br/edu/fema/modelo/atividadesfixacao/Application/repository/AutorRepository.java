package br.edu.fema.modelo.atividadesfixacao.Application.repository;

import br.edu.fema.modelo.atividadesfixacao.Application.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
