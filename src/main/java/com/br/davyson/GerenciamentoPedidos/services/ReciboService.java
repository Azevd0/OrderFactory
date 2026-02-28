package com.br.davyson.GerenciamentoPedidos.services;

import com.br.davyson.GerenciamentoPedidos.dto.FaturamentoResponseDTO;
import com.br.davyson.GerenciamentoPedidos.dto.ReciboResponseDTO;
import com.br.davyson.GerenciamentoPedidos.entitys.Recibo;
import com.br.davyson.GerenciamentoPedidos.repositorys.ReciboRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReciboService {
    private final ReciboRepository reciboRepository;

    public ReciboService(ReciboRepository reciboRepository) {
        this.reciboRepository = reciboRepository;
    }

    public List<ReciboResponseDTO> listarHistorico(String periodo) {
        if (periodo.equalsIgnoreCase("tudo")) {
            return reciboRepository.findAll().stream()
                    .map(ReciboResponseDTO::new)
                    .toList();
        }
        LocalDateTime dataLimite = switch (periodo.toLowerCase()) {
            case "semana" -> LocalDateTime.now().minusWeeks(1);
            case "quinzena" -> LocalDateTime.now().minusWeeks(2);
            default -> throw new IllegalArgumentException("Período inválido: " + periodo);
        };
        return reciboRepository.findByDataFechamentoAfter(dataLimite).stream()
                .map(ReciboResponseDTO::new)
                .toList();
    }
    private BigDecimal somarPorPeriodo(LocalDateTime data) {
        return reciboRepository.findByDataFechamentoAfter(data).stream()
                .map(Recibo::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public FaturamentoResponseDTO calcularFaturamento() {
        BigDecimal semana = somarPorPeriodo(LocalDateTime.now().minusWeeks(1));
        BigDecimal quinzena = somarPorPeriodo(LocalDateTime.now().minusWeeks(2));
        BigDecimal total = reciboRepository.findAll().stream()
                .map(Recibo::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new FaturamentoResponseDTO(semana, quinzena, total);
    }


}
