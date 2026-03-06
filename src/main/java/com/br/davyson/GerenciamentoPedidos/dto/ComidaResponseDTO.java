package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Comida;

import java.math.BigDecimal;

public class ComidaResponseDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String categoriaNome;
    private String descricao;

    public ComidaResponseDTO(){}
    public ComidaResponseDTO(Comida comida){
        this.id = comida.getId();
        this.nome = comida.getNome();
        this.preco = comida.getPreco();
        this.descricao = comida.getDescricao();

        if (comida.getCategoria() != null) {
            this.categoriaNome = comida.getCategoria().getNome();
        } else {
            this.categoriaNome = "Sem Categoria";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
