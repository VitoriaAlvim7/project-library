package br.edu.fema.modelo.atividadesfixacao.Application.service;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.LivroDto;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Autor;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Categoria;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Emprestimo;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Livro;
import br.edu.fema.modelo.atividadesfixacao.Application.repository.LivroRepository;
import br.edu.fema.modelo.atividadesfixacao.Application.exceptions.custom.ObjectNotFoundException;
import br.edu.fema.modelo.atividadesfixacao.Application.service.enums.Situacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDto> buscarLivro() {
        List<Livro> listaLivro = this.livroRepository.findAll();
        List<LivroDto> listaLivroDto = LivroDto.converter(listaLivro);
        return listaLivroDto;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    public LivroDto buscarPorId(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
         Livro buscaLivro = livro.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
         return new LivroDto(buscaLivro, null, null);
    }

    public LivroDto atualizarLivro(Long id, Livro livro) {
        Livro livroAntigo = livroRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

        livroAntigo.setTitulo(livro.getTitulo());
        livroAntigo.setAutor(livro.getAutor());
        livroAntigo.setPreco(livro.getPreco());
        livroAntigo.setDataPublicacao(livro.getDataPublicacao());
        livroAntigo.setSituacao(livro.getSituacao());
        livroAntigo.setCategorias(livro.getCategorias());
        livroAntigo.setEmprestimo(livro.getEmprestimo());

        Livro livroAtualizado = livroRepository.save(livroAntigo);
        return new LivroDto(livroAtualizado, null, null);
    }

    public LivroDto criarLivro(LivroDto livroDto) {

        Livro livro = new Livro();

        livro.setTitulo(livroDto.getTitulo());
        livro.setPreco(livroDto.getPreco());
        livro.setDataPublicacao(livroDto.getDataPublicacao());
        livro.setSituacao(livroDto.getSituacao());

        if (livroDto.getCategorias() != null) {
            livro.setCategorias(livroDto.getCategorias().stream().map
                            (categoriaDto -> {Categoria categoria = new Categoria();
                                categoria.setId(categoriaDto.getId());
                                categoria.setNome(categoriaDto.getNome());
                                return categoria;}).collect(Collectors.toList()));
        }

        if (livroDto.getEmprestimoDto() != null) {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setId(livroDto.getEmprestimoDto().getId());
            livro.setEmprestimo(emprestimo);
        }

        if (livroDto.getAutorDto() != null) {
            Autor autor = new Autor();
            autor.setId(livroDto.getAutorDto().getId());
            livro.setAutor(autor);
        }

        livro = livroRepository.save(livro);
        return new LivroDto(livro, livroDto.getCategorias(), livroDto.getEmprestimoDto());
    }

    public void deletarLivroPorId(Long id) {
        buscarPorId(id);
        livroRepository.deleteById(id);
    }

    public List<LivroDto> buscarPorTituloEPreco(String titulo, BigDecimal preco) {
        List<Livro> livros = livroRepository.findByTituloAndPreco(titulo, preco);
        return LivroDto.converter(livros);
    }

    public List<LivroDto> buscarPorTituloOuPreco(String titulo, BigDecimal preco) {
        List<Livro> livros = livroRepository.findByTituloOrPreco(titulo, preco);
        return LivroDto.converter(livros);
    }

    public List<LivroDto> buscarLivroDisponivel() {
        List<Livro> listaLivro = livroRepository.findAll().stream()
                .filter(livro -> livro.getSituacao().equals(Situacao.DISPONIVEL)).collect(Collectors.toList());

        List<LivroDto> livroDisponivel = LivroDto.converter(listaLivro);
        return livroDisponivel;
    }
}
