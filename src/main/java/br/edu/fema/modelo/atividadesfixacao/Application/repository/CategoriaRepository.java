package br.edu.fema.modelo.atividadesfixacao.Application.repository;

import br.edu.fema.modelo.atividadesfixacao.Application.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findFirstByNome(String nome);

    void deleteCategoriaById (Long id);

    List<Categoria> findAllByOrderByIdDesc();

    List<Categoria> findAllByOrderByIdAsc();

    List<Categoria> findByIdIn(List<Long> ids);
}
