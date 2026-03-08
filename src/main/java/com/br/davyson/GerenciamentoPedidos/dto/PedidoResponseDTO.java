package com.br.davyson.GerenciamentoPedidos.dto;
import com.br.davyson.GerenciamentoPedidos.entitys.Comida;
import com.br.davyson.GerenciamentoPedidos.entitys.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO (Long id, Integer mesa, String nomeAtendente, List<String>comidas,
                                 String observacao, BigDecimal subTotal, BigDecimal taxaServico, BigDecimal valorTotal,
                                    BigDecimal valorPago, BigDecimal saldoRestante, LocalDateTime data){

    public PedidoResponseDTO(Pedido pedido){
        this(
                pedido.getId(),
                pedido.getMesa(),
                pedido.getAtendente()!= null ? pedido.getAtendente().getNome() : "Não atribuído",
                pedido.getComidas().stream().map(Comida::getNome).toList(),
                pedido.getObservacao(),
                pedido.getSubtotal(),
                pedido.getTaxaServico(),
                pedido.getValorTotal(),
                pedido.getValorPago(),
                pedido.getSaldoRestante(),
                pedido.getData()
        );
    }
}
