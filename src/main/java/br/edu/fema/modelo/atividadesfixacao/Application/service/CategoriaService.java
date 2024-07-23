package br.edu.fema.modelo.atividadesfixacao.Application.service;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.CategoriaDto;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Categoria;
import br.edu.fema.modelo.atividadesfixacao.Application.repository.CategoriaRepository;
import br.edu.fema.modelo.atividadesfixacao.Application.exceptions.custom.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public CategoriaDto buscarPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        Categoria buscaCategoria = categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));
        return new CategoriaDto(buscaCategoria);
    }

    public CategoriaDto atualizarCategoria(Long id, CategoriaDto categoriaDto) {
        Categoria categoriaAntiga = categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));

        categoriaAntiga.setNome(categoriaDto.getNome());

        Categoria categoriaAtualizada = categoriaRepository.save(categoriaAntiga);
        return new CategoriaDto(categoriaAtualizada);
    }

    public CategoriaDto criarCategoria(CategoriaDto categoriaDto) {

        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDto.getNome());

        categoria = categoriaRepository.save(categoria);
        return categoriaDto;
    }

    public void deletarCategoria(Long id) {
        buscarPorId(id);
        categoriaRepository.deleteById(id);
    }

    public CategoriaDto buscarPrimeiraCategoria(String nome) {
        Optional<Categoria> categoria = categoriaRepository.findFirstByNome(nome);
        Categoria buscaCategoria = categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));
        return new CategoriaDto(buscaCategoria);
    }

    @Transactional
    public void deletarCategoriaPorId(Long id) {
        categoriaRepository.deleteCategoriaById(id);
    }

    public List<CategoriaDto> listarCategoriasOrdenadasDesc() {
        List<Categoria> categorias = categoriaRepository.findAllByOrderByIdDesc();
        return CategoriaDto.converter(categorias);
    }

    public List<CategoriaDto> listarCategoriasOrdenadasAsc() {
        List<Categoria> categorias = categoriaRepository.findAllByOrderByIdAsc();
        return CategoriaDto.converter(categorias);
    }

    public List<CategoriaDto> listarCategoriasPorIds(List<Long> ids) {
        List<Categoria> categorias = categoriaRepository.findByIdIn(ids);
        return CategoriaDto.converter(categorias);
    }

}
