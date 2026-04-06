package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Comida;

import java.math.BigDecimal;

public record ComidaRequestDTO(String nome, String descricao, BigDecimal preco) {

    public ComidaRequestDTO(Comida comida){
        this(
                comida.getNome(),
                comida.getDescricao(),
                comida.getPreco()
        );
    }
}
