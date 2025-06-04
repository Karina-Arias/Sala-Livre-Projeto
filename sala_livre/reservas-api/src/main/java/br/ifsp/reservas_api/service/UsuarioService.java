package br.ifsp.reservas_api.service;

import br.ifsp.reservas_api.model.Usuario;
import br.ifsp.reservas_api.model.Sala;
import br.ifsp.reservas_api.model.Reserva;
import br.ifsp.reservas_api.dto.ReservaDTO;
import br.ifsp.reservas_api.repository.UsuarioRepository;
import br.ifsp.reservas_api.repository.SalaRepository;
import br.ifsp.reservas_api.repository.ReservaRepository;
import br.ifsp.reservas_api.config.JwUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private JwUtil jwtUtil;

    public Usuario cadastrarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    public boolean senhaCorreta(String senhaCriptografada, String senhaInformada) {
        return passwordEncoder.matches(senhaInformada, senhaCriptografada);
    }

    public String gerarToken(Usuario usuario) {
        return jwtUtil.gerarToken(usuario.getEmail());
    }

}