package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Atendente;

public class AtendenteResponseDTO {
    private Long id;
    private String nome;

    public AtendenteResponseDTO() {}

    public AtendenteResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public AtendenteResponseDTO(Atendente atendente) {
        this.id = atendente.getId();
        this.nome = atendente.getNome();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
}
