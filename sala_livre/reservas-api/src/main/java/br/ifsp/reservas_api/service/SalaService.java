package br.ifsp.reservas_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsp.reservas_api.model.Sala;
import br.ifsp.reservas_api.repository.SalaRepository;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala cadastrarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    public Optional<Sala> buscarPorId(Long id) {
        return salaRepository.findById(id);
    }
    
    public Sala editarSala(Long id, Sala salaAtualizada) {
        Optional<Sala> salaExistente = salaRepository.findById(id);
        if (salaExistente.isPresent()) {
            Sala sala = salaExistente.get();
            
            sala.setNome(salaAtualizada.getNome()); 
            sala.setDescricao(salaAtualizada.getDescricao()); 
            return salaRepository.save(sala);  
        } else {
            throw new RuntimeException("Sala não encontrada com ID: " + id);
        }
    }

    public void deletarSala(Long id) {
        salaRepository.deleteById(id);
    }
}
