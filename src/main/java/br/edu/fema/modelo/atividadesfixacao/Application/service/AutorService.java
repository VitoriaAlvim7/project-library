package br.edu.fema.modelo.atividadesfixacao.Application.service;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.AutorDto;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Autor;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Biografia;
import br.edu.fema.modelo.atividadesfixacao.Application.repository.AutorRepository;
import br.edu.fema.modelo.atividadesfixacao.Application.exceptions.custom.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;


    public AutorDto buscarPorId(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        Autor buscaAutor = autor.orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado"));
        return new AutorDto(buscaAutor);
    }

    public AutorDto atualizarAutor(Long id, AutorDto autor) {
        Autor autorAntigo = autorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado"));

        autorAntigo.setNome(autor.getNome());

        Autor autorAtualizado = autorRepository.save(autorAntigo);
        return new AutorDto(autorAtualizado);
    }

    public AutorDto criarAutor(AutorDto autorDto) {

        Autor autor = new Autor();
        autor.setNome(autorDto.getNome());
        if (autorDto.getBiografiaDto() != null) {
            Biografia biografia = autorDto.getBiografiaDto().toEntity();
            biografia.setAutor(autor);
            autor.setBiografia(biografia);
        }
        autor = autorRepository.save(autor);
        return autorDto;
    }

    public void deletarAutor(Long id) {
        buscarPorId(id);
        autorRepository.deleteById(id);
    }
}
