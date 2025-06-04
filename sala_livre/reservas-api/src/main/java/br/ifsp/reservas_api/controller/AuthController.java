package br.ifsp.reservas_api.controller;

import br.ifsp.reservas_api.model.Usuario;
import br.ifsp.reservas_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario login) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(login.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuarioService.senhaCorreta(login.getSenha(), usuario.getSenha())) {
                String token = usuarioService.gerarToken(usuario);
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}

