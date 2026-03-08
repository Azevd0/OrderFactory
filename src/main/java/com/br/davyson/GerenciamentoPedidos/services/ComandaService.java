package com.br.davyson.GerenciamentoPedidos.services;

import com.br.davyson.GerenciamentoPedidos.entitys.Comanda;
import com.br.davyson.GerenciamentoPedidos.enums.Periodo;
import com.br.davyson.GerenciamentoPedidos.repositorys.ComandaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComandaService {
    private final ComandaRepository comandaRepository;

    public ComandaService(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    public List<Comanda> listarHistorico(Periodo periodo) {
        LocalDateTime dataLimite = switch (periodo) {
            case TOTAL_HOJE -> LocalDate.now().atStartOfDay();
            case TOTAL_SEMANA -> LocalDateTime.now().minusWeeks(1);
            case TOTAL_QUINZENA -> LocalDateTime.now().minusWeeks(2);
            case TOTAL_MENSAL -> LocalDateTime.now().minusMonths(1);
        };

        return comandaRepository.findByDataLancamentoAfter(dataLimite);
    }


}
