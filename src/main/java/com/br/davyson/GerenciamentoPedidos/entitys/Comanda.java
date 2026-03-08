package com.br.davyson.GerenciamentoPedidos.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comanda")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mesa;

    private String atendenteNome;

    private String comidaNome;

    private LocalDateTime dataLancamento = LocalDateTime.now();

    public Comanda(Long id, Integer mesa, Atendente atendente, Comida comida, LocalDateTime dataLancamento) {
        this.id = id;
        this.mesa = mesa;
        this.atendenteNome = atendente.getNome();
        this.comidaNome = comida.getNome();
        this.dataLancamento = dataLancamento;
    }
    public Comanda(){}

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

    public String getAtendenteNome() {
        return atendenteNome;
    }

    public void setAtendenteNome(String atendenteNome) {
        this.atendenteNome = atendenteNome;
    }

    public String getComidaNome() {
        return comidaNome;
    }

    public void setComidaNome(String comidaNome) {
        this.comidaNome = comidaNome;
    }

    public LocalDateTime getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDateTime dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
