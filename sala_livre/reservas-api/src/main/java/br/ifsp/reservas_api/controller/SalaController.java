package br.ifsp.reservas_api.controller;

import br.ifsp.reservas_api.model.Sala;
import br.ifsp.reservas_api.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public List<Sala> listarSalas() {
        return salaService.listarSalas();
    }

    @PostMapping
    public ResponseEntity<Sala> cadastrar(@RequestBody Sala sala) {
        return ResponseEntity.ok(salaService.cadastrarSala(sala));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> editar(@PathVariable Long id, @RequestBody Sala salaAtualizada) {
        return ResponseEntity.ok(salaService.editarSala(id, salaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        salaService.deletarSala(id);
        return ResponseEntity.noContent().build();
    }
}
