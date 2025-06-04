package br.ifsp.reservas_api.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsp.reservas_api.dto.ReservaDTO;
import br.ifsp.reservas_api.model.Reserva;
import br.ifsp.reservas_api.model.Sala;
import br.ifsp.reservas_api.model.Usuario;
import br.ifsp.reservas_api.repository.ReservaRepository;
import br.ifsp.reservas_api.repository.SalaRepository;
import br.ifsp.reservas_api.repository.UsuarioRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private UsuarioRepository userRepository;

    public Reserva agendar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservasDoUsuario(String emailUsuario) {
        return reservaRepository.findByUsuarioEmail(emailUsuario);
    }

    public List<Reserva> listarTodasReservas() {
        return reservaRepository.findAll();
    }

    public List<Reserva> listarPorSala(Long idSala) {
        return reservaRepository.findBySalaId(idSala); 
    }
    
    public List<Reserva> buscarPorUsuario(String usuario) {
        return reservaRepository.findByUsuario(usuario);
    }

    public void cancelar(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<Reserva> buscarPorSalaDataHorario(Long salaId, LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
        return reservaRepository.findBySalaIdAndDataAndHoraInicioLessThanAndHoraFimGreaterThan(salaId, data, horaFim, horaInicio);
    }
}
