package com.br.davyson.GerenciamentoPedidos.entitys;

import com.br.davyson.GerenciamentoPedidos.enums.BandeiraCartao;
import com.br.davyson.GerenciamentoPedidos.enums.ModalidadaCartao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "cartao")
public class CartaoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeiraCartao;
    private ModalidadaCartao formaPagamento;
    @NotNull(message = "O cartão deve ter um saldo")
    private BigDecimal saldo;
    @Size(min = 4, max = 6, message = "A senha deve ter entre 4 e 6 caracteres")
    private String senha;

    public CartaoCliente(){}

    public CartaoCliente(Long id, BandeiraCartao bandeiraCartao, ModalidadaCartao formaPagamento, BigDecimal saldo, String senha) {
        this.id = id;
        this.bandeiraCartao = bandeiraCartao;
        this.formaPagamento = formaPagamento;
        this.saldo = saldo;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BandeiraCartao getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    public ModalidadaCartao getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(ModalidadaCartao formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
