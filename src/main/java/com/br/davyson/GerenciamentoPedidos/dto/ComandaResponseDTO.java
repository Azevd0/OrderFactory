package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Comida;
import com.br.davyson.GerenciamentoPedidos.entitys.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ComandaResponseDTO(Integer mesa, String atendenteNome,
                                 String comidaNome,
                                 @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                 LocalDateTime data) {

    public ComandaResponseDTO(Pedido pedido, Comida comida) {
        this(
                pedido.getMesa(),
                pedido.getAtendente().getNome(),
                comida.getNome(),
                LocalDateTime.now()
        );
    }
}
