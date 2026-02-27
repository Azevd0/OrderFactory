package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Comida;
import com.br.davyson.GerenciamentoPedidos.entitys.Pedido;
import com.br.davyson.GerenciamentoPedidos.enums.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {
    private Long id;
    private Integer mesa;
    private String nomeAtendente;
    private List<String> comidas;
    private String observacao;
    private BigDecimal subTotal;
    private BigDecimal taxaServico;
    private BigDecimal valorTotal;
    private BigDecimal valorPago;
    private FormaPagamento formaDePagamento;
    private BigDecimal saldoRestante;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.mesa = pedido.getMesa();
        this.nomeAtendente = pedido.getAtendente().getNome();
        this.comidas = pedido.getComidas().stream().map(Comida::getNome).toList();
        this.observacao = pedido.getObservacao();
        this.subTotal = pedido.getSubtotal();
        this.taxaServico = pedido.getTaxaServico();
        this.valorTotal = pedido.getValorTotal();
        this.valorPago = pedido.getValorPago();
        this.formaDePagamento = pedido.getFormaDePagamento();
        this.saldoRestante = this.valorTotal.subtract(this.valorPago).max(BigDecimal.ZERO);
        this.data = pedido.getData();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public List<String> getComidas() {
        return comidas;
    }

    public void setComidas(List<String> comidas) {
        this.comidas = comidas;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getTaxaServico() {
        return taxaServico;
    }

    public void setTaxaServico(BigDecimal taxaServico) {
        this.taxaServico = taxaServico;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public FormaPagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaPagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public BigDecimal getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(BigDecimal saldoRestante) {
        this.saldoRestante = saldoRestante;
    }

    public LocalDateTime getData() {
        return this.data;
    }
}
