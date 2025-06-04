package br.ifsp.reservas_api.model;

import javax.management.relation.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private String email;
    private String senha;

    public Usuario(Long id, String usuario, String email, String senha, Role cargo) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
		this.cargo = cargo;
	}

	@Enumerated(EnumType.STRING)
    private Role cargo;

    public enum Role {
        USER, ADMIN
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Role getCargo() {
		return cargo;
	}

	public void setCargo(Role cargo) {
		this.cargo = cargo;
	}  
}    

