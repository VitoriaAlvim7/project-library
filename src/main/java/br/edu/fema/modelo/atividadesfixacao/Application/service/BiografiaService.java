package br.edu.fema.modelo.atividadesfixacao.Application.service;

import br.edu.fema.modelo.atividadesfixacao.Application.dto.BiografiaDto;
import br.edu.fema.modelo.atividadesfixacao.Application.model.Biografia;
import br.edu.fema.modelo.atividadesfixacao.Application.model.BiografiaId;
import br.edu.fema.modelo.atividadesfixacao.Application.repository.BiografiaRepository;
import br.edu.fema.modelo.atividadesfixacao.Application.exceptions.custom.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BiografiaService {

    @Autowired
    private BiografiaRepository biografiaRepository;

    public BiografiaDto buscarPorId(BiografiaId id) {
        Optional<Biografia> biografia = biografiaRepository.findById(id);
        Biografia buscaBiografia = biografia.orElseThrow(() -> new ObjectNotFoundException("Biografia não encontrada"));
        return new BiografiaDto(buscaBiografia);
    }

    public BiografiaDto atualizarBiografia(BiografiaId id, BiografiaDto biografiaDto) {
        Biografia biografiaAntiga = biografiaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Biografia não encontrada"));

        biografiaAntiga.setDetalhes(biografiaDto.getDetalhes());

        Biografia biografiaAtualizada = biografiaRepository.save(biografiaAntiga);
        return new BiografiaDto(biografiaAtualizada);
    }

    public BiografiaDto criarBiografia(BiografiaId id, BiografiaDto biografiaDto) {

        Biografia biografia = new Biografia();
        biografia.setId(id);
        biografia.setDetalhes(biografiaDto.getDetalhes());

        biografia = biografiaRepository.save(biografia);

        biografiaDto.setAutorId(id.getAutorId());
        biografiaDto.setBiografiaId(id.getBiografiaId());

        return biografiaDto;
    }

    public void deletarBiografia(BiografiaId biografiaId) {
        BiografiaDto biografiaDto  = buscarPorId(biografiaId);
        biografiaRepository.deleteById(biografiaId);
    }


}
