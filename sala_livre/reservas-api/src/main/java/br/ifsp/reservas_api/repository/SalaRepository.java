package br.ifsp.reservas_api.repository;

import br.ifsp.reservas_api.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {
}