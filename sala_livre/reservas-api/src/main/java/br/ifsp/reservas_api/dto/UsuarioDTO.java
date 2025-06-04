package br.ifsp.reservas_api.dto;

public class UsuarioDTO {
    private String usuario;
    private String email;
    private String senha;

    public UsuarioDTO() {}

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
