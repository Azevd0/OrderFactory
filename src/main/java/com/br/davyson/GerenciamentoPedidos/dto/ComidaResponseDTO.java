package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Comida;

import java.math.BigDecimal;

public class ComidaResponseDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private String categoriaNome;

    public ComidaResponseDTO() {}

    public ComidaResponseDTO(Long id, String nome, BigDecimal preco, String descricao, String categoriaNome) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.categoriaNome = categoriaNome;
    }

    public ComidaResponseDTO(Comida comida) {
        this.id = comida.getId();
        this.nome = comida.getNome();
        this.preco = comida.getPreco();
        this.descricao = comida.getDescricao();
        this.categoriaNome = comida.getCategoria() != null ? comida.getCategoria().getNome() : "Sem Categoria";
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public String getDescricao() { return descricao; }
    public String getCategoriaNome() { return categoriaNome; }
}
