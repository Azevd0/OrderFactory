package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.CartaoCliente;

import java.math.BigDecimal;

public record CartaoClienteResponseDTO(Long id, String bandeiraCartao, BigDecimal saldo){

    public CartaoClienteResponseDTO(CartaoCliente cartaoCliente){
        this(
                cartaoCliente.getId(),
                cartaoCliente.getBandeiraCartao() != null ? cartaoCliente.getBandeiraCartao().name(): null,
                cartaoCliente.getSaldo()
        );
    }
}
