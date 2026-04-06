package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Categoria;

public class CategoriaResponseDTO {
    private Long id;
    private String nome;

    public CategoriaResponseDTO() {}

    public CategoriaResponseDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
}
