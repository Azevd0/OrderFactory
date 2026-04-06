package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Recibo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReciboResponseDTO {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataDeFechamento;

    private BigDecimal valorTotal;
    private String formaDePagamento;
    private String bandeiraCartao;
    private Integer qtdDePessoas;
    private BigDecimal mediaPorPessoa;

    public ReciboResponseDTO() {
    }

    public ReciboResponseDTO(Long id, LocalDateTime dataDeFechamento, BigDecimal valorTotal,
                             String formaDePagamento, String bandeiraCartao, Integer qtdDePessoas,
                             BigDecimal mediaPorPessoa) {
        this.id = id;
        this.dataDeFechamento = dataDeFechamento;
        this.valorTotal = valorTotal;
        this.formaDePagamento = formaDePagamento;
        this.bandeiraCartao = bandeiraCartao;
        this.qtdDePessoas = qtdDePessoas;
        this.mediaPorPessoa = mediaPorPessoa;
    }

    public ReciboResponseDTO(Recibo recibo) {
        this.id = recibo.getId();
        this.dataDeFechamento = recibo.getDataFechamento();
        this.valorTotal = recibo.getValorTotal();
        this.formaDePagamento = recibo.getFormaPagamento() != null ? recibo.getFormaPagamento().name() : null;
        this.bandeiraCartao = recibo.getBandeiraCartao() != null ? recibo.getBandeiraCartao().name() : null;
        this.qtdDePessoas = recibo.getQtdDePessoas();
        this.mediaPorPessoa = recibo.getMedia();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataDeFechamento() { return dataDeFechamento; }
    public void setDataDeFechamento(LocalDateTime dataDeFechamento) { this.dataDeFechamento = dataDeFechamento; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getFormaDePagamento() { return formaDePagamento; }
    public void setFormaDePagamento(String formaDePagamento) { this.formaDePagamento = formaDePagamento; }

    public String getBandeiraCartao() { return bandeiraCartao; }
    public void setBandeiraCartao(String bandeiraCartao) { this.bandeiraCartao = bandeiraCartao; }

    public Integer getQtdDePessoas() { return qtdDePessoas; }
    public void setQtdDePessoas(Integer qtdDePessoas) { this.qtdDePessoas = qtdDePessoas; }

    public BigDecimal getMediaPorPessoa() { return mediaPorPessoa; }
    public void setMediaPorPessoa(BigDecimal mediaPorPessoa) { this.mediaPorPessoa = mediaPorPessoa; }
}