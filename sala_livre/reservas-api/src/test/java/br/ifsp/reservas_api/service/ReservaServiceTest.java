package br.ifsp.reservas_api.service;

import br.ifsp.reservas_api.model.Reserva;
import br.ifsp.reservas_api.model.Sala;
import br.ifsp.reservas_api.model.Usuario;
import br.ifsp.reservas_api.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaServiceTest {

    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarReservaSemConflito() {
        Sala sala = new Sala();
        sala.setId(1L);
        sala.setNome("Sala A");
        Usuario usuario = new Usuario(1L, "João", "joao@email.com", "senha", Usuario.Role.USER);
        Reserva reserva = new Reserva(null, sala, usuario, LocalDate.of(2025, 6, 10), LocalTime.of(14, 0), LocalTime.of(15, 0));

        when(reservaRepository.findBySalaIdAndDataAndHoraInicioLessThanAndHoraFimGreaterThan(
                eq(sala.getId()), any(), any(), any())).thenReturn(List.of());

        when(reservaRepository.save(any())).thenReturn(reserva);

        Reserva salva = reservaService.agendar(reserva);

        assertNotNull(salva);
        verify(reservaRepository).save(reserva);
    }

    @Test
    void naoDeveCriarReservaComConflito() {
    	Sala sala = new Sala();
        sala.setId(1L);
        sala.setNome("Sala A");
        Usuario usuario = new Usuario(1L, "Maria", "maria@email.com", "senha", Usuario.Role.USER);
        Reserva reserva = new Reserva(null, sala, usuario, LocalDate.of(2025, 6, 10), LocalTime.of(14, 0), LocalTime.of(15, 0));

        when(reservaRepository.findBySalaIdAndDataAndHoraInicioLessThanAndHoraFimGreaterThan(
                eq(sala.getId()), any(), any(), any()))
            .thenReturn(List.of(new Reserva())); 

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            reservaService.agendar(reserva);
        });

        assertEquals("Horário já reservado para esta sala.", ex.getMessage());
        verify(reservaRepository, never()).save(any());
    }
}
