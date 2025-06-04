package br.ifsp.reservas_api.controller;

import br.ifsp.reservas_api.model.Reserva;
import br.ifsp.reservas_api.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.listarTodasReservas();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Reserva> listarReservasDoUsuario(@PathVariable String emailUsuario) {
        return reservaService.listarReservasDoUsuario(emailUsuario);
    }

    @GetMapping("/sala/{idSala}")
    public List<Reserva> listarPorSala(@PathVariable Long idSala) {
        return reservaService.listarPorSala(idSala);
    }

    @PostMapping
    public ResponseEntity<?> agendar(@RequestBody Reserva reserva) {
        try {
            Reserva agendada = reservaService.agendar(reserva);
            return ResponseEntity.ok(agendada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        reservaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
