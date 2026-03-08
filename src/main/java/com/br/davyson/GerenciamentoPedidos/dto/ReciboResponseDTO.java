package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Recibo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
public record ReciboResponseDTO (Long id, LocalDateTime dataDeFechamento,
                                 BigDecimal valorTotal, String formaDePagamento, Integer qtdDePessoas,
                                 BigDecimal mediaPorPessoa){

    public ReciboResponseDTO(Recibo recibo){
        this(
                recibo.getId(),
                recibo.getDataFechamento(),
                recibo.getValorTotal(),
                recibo.getFormaPagamento() != null ? recibo.getFormaPagamento().name() : null,
                recibo.getQtdDePessoas(),
                recibo.getMedia()
        );
    }
}
