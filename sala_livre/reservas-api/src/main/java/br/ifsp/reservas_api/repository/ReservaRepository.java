package br.ifsp.reservas_api.repository;

import br.ifsp.reservas_api.model.Reserva;
import br.ifsp.reservas_api.model.Sala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findBySalaIdAndDataAndHoraInicioLessThanAndHoraFimGreaterThan(
        Long salaId,
        LocalDate data,
        LocalTime horaFim,
        LocalTime horaInicio
    );

    List<Reserva> findByUsuarioEmail(String email);
    List<Reserva> findBySalaId(Long salaId); 
    List<Reserva> findByUsuario(String usuario);
}