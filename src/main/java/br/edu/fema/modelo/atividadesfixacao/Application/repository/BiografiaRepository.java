package br.edu.fema.modelo.atividadesfixacao.Application.repository;

import br.edu.fema.modelo.atividadesfixacao.Application.model.Biografia;
import br.edu.fema.modelo.atividadesfixacao.Application.model.BiografiaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiografiaRepository extends JpaRepository<Biografia, BiografiaId> {

}
