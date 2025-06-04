package br.ifsp.reservas_api.service;

import br.ifsp.reservas_api.model.Usuario;
import br.ifsp.reservas_api.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarUsuarioComSenhaCriptografada() {
        Usuario usuario = new Usuario(null, "Ana", "ana@email.com", "123456", Usuario.Role.USER);
        String senhaCriptografada = "$2a$10$senhaCriptografada";

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(encoder.encode(usuario.getSenha())).thenReturn(senhaCriptografada);
        when(usuarioRepository.save(any())).thenReturn(usuario);

        Usuario salvo = usuarioService.cadastrarUsuario(usuario);

        assertNotNull(salvo);
        verify(encoder).encode("123456");
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void naoDeveCadastrarUsuarioComEmailDuplicado() {
        Usuario usuario = new Usuario(null, "Ana", "ana@email.com", "123456", Usuario.Role.USER);

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrarUsuario(usuario);
        });

        assertEquals("Email já cadastrado", ex.getMessage());
        verify(usuarioRepository, never()).save(any());
    }
}

