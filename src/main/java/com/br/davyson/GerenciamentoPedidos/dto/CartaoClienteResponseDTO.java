package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.CartaoCliente;

import java.math.BigDecimal;

public class CartaoClienteResponseDTO {
    private Long id;
    private String bandeiraCartao;
    private String modalidade;
    private BigDecimal saldo;

    public CartaoClienteResponseDTO() {}

    public CartaoClienteResponseDTO(Long id, String bandeiraCartao, String modalidade, BigDecimal saldo) {
        this.id = id;
        this.bandeiraCartao = bandeiraCartao;
        this.modalidade = modalidade;
        this.saldo = saldo;
    }

    public CartaoClienteResponseDTO(CartaoCliente cartaoCliente) {
        this.id = cartaoCliente.getId();
        this.bandeiraCartao = cartaoCliente.getBandeiraCartao() != null ? cartaoCliente.getBandeiraCartao().name() : null;
        this.modalidade = cartaoCliente.getFormaPagamento() != null ? cartaoCliente.getFormaPagamento().name() : null;
        this.saldo = cartaoCliente.getSaldo();
    }

    public Long getId() { return id; }
    public String getBandeiraCartao() { return bandeiraCartao; }
    public String getModalidade() { return modalidade; }
    public BigDecimal getSaldo() { return saldo; }
}
