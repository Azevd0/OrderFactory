package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Atendente;

public record AtendenteResponseDTO (Long id, String nome){
    public AtendenteResponseDTO(Atendente atendente) {
        this(
                atendente.getId(),
                atendente.getNome()
        );
    }
}
