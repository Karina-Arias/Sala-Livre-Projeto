package br.ifsp.reservas_api.controller;

import br.ifsp.reservas_api.model.Usuario;
import br.ifsp.reservas_api.service.UsuarioService;
import br.ifsp.reservas_api.repository.UsuarioRepository;
import br.ifsp.reservas_api.config.JwUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;
    
    @MockBean
    private UsuarioService usuarioService;


    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private JwUtil jwtUtil;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void deveRetornarTokenAoFazerLoginComCredenciaisValidas() throws Exception {
        String email = "user@example.com";
        String senha = "123456";
        String token = "jwt.token.mock";

        Usuario usuario = new Usuario(1L, "User Test", email, senha, Usuario.Role.USER);

        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        Mockito.when(passwordEncoder.matches(senha, usuario.getSenha())).thenReturn(true);
        Mockito.when(jwtUtil.gerarToken(Mockito.any())).thenReturn(token);

        String jsonLogin = "{\"email\":\"" + email + "\", \"senha\":\"" + senha + "\"}";

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonLogin))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(token));
    }
}
