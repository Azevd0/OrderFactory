package com.br.davyson.GerenciamentoPedidos.dto;

import com.br.davyson.GerenciamentoPedidos.entitys.Comanda;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

public class ComandaDTO {

    private Long id;
    private Integer mesa;
    private String atendenteNome;
    private List<String> comidaNome;
    private String observacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataLancamento;

    public ComandaDTO() {}

    public ComandaDTO(Long id, Integer mesa, String atendenteNome, List<String> comidaNome, String observacao, LocalDateTime dataLancamento) {
        this.id = id;
        this.mesa = mesa;
        this.atendenteNome = atendenteNome;
        this.comidaNome = comidaNome;
        this.observacao = observacao;
        this.dataLancamento = dataLancamento;
    }

    public ComandaDTO(Comanda comanda) {
        this.id = comanda.getId();
        this.mesa = comanda.getMesa();
        this.atendenteNome = comanda.getAtendenteNome();
        this.comidaNome = comanda.getComidaNome();
        this.observacao = comanda.getObservacao();
        this.dataLancamento = comanda.getDataLancamento();
    }

    public Long getId() { return id; }
    public Integer getMesa() { return mesa; }
    public String getAtendenteNome() { return atendenteNome; }
    public List<String> getComidaNome() { return comidaNome; }
    public String getObservacao() { return observacao; }
    public LocalDateTime getDataLancamento() { return dataLancamento; }
}
