package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Comida;

import java.math.BigDecimal;

public record ComidaResponseDTO (Long id, String nome, BigDecimal preco, String descricao, String categoriaNome){

    public ComidaResponseDTO(Comida comida){
        this(
                comida.getId(),
                comida.getNome(),
                comida.getPreco(),
                comida.getDescricao(),
                comida.getCategoria() != null ? comida.getCategoria().getNome() : "Sem Categoria"

        );
    }
}
