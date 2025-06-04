package br.ifsp.reservas_api.dto;

public class SalaDTO {
    private String nome;
    private String descricao;

    public SalaDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}

