package br.ifsp.reservas_api.controller;

import br.ifsp.reservas_api.model.Reserva;
import br.ifsp.reservas_api.repository.UsuarioRepository;
import br.ifsp.reservas_api.model.Sala;
import br.ifsp.reservas_api.model.Usuario;
import br.ifsp.reservas_api.service.ReservaService;
import br.ifsp.reservas_api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.Optional;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private UsuarioRepository usuarioRepository;

    @MockBean
    private ReservaService reservaService;

    @MockBean
    private UsuarioService usuarioService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "user@example.com", roles = {"USER"})
    void deveRetornarListaDeReservasDoUsuario() throws Exception {
        Usuario usuario = new Usuario(1L, "User", "user@example.com", "senha", Usuario.Role.USER);
        Sala sala = new Sala();
        sala.setId(1L);
        sala.setNome("Sala A");
        Reserva reserva = new Reserva(1L, sala, usuario, LocalDate.now(), LocalTime.of(14,0), LocalTime.of(15,0));

        Mockito.when(usuarioRepository.findByEmail("teste@email.com"))
        .thenReturn(Optional.of(usuario)); 

        Mockito.when(reservaService.buscarPorUsuario(usuario.getUsuario())).thenReturn(List.of(reserva));

        mockMvc.perform(get("/reservas/minhas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithMockUser(username = "user@example.com", roles = {"USER"})
    void deveCriarReservaComUsuarioAutenticado() throws Exception {
        Usuario usuario = new Usuario(1L, "User", "user@example.com", "senha", Usuario.Role.USER);
        Sala sala = new Sala();
        sala.setId(1L);
        sala.setNome("Sala A");
        Reserva novaReserva = new Reserva(null, sala, usuario, LocalDate.of(2025,6,10), LocalTime.of(10,0), LocalTime.of(11,0));
        Reserva reservaSalva = new Reserva(1L, sala, usuario, LocalDate.of(2025,6,10), LocalTime.of(10,0), LocalTime.of(11,0));

        Mockito.when(usuarioRepository.findByEmail("teste@email.com")).thenReturn(Optional.of(usuario)); 

        Mockito.when(reservaService.agendar(Mockito.any())).thenReturn(reservaSalva);

        String jsonReserva = objectMapper.writeValueAsString(novaReserva);

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReserva))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
}
