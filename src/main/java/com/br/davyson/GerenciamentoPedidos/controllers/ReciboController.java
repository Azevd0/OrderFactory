package com.br.davyson.GerenciamentoPedidos.controllers;

import com.br.davyson.GerenciamentoPedidos.dto.FaturamentoResponseDTO;
import com.br.davyson.GerenciamentoPedidos.dto.ReciboResponseDTO;
import com.br.davyson.GerenciamentoPedidos.services.ReciboService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historico")
@Tag(name = "Histórico Financeiro", description = "Relatório de vendas")
public class ReciboController {
    private final ReciboService reciboService;

    public ReciboController(ReciboService reciboService) {
        this.reciboService = reciboService;
    }

    @Operation(summary = "Listar histórico de pagamentos")
    @GetMapping("/historico-de-vendas")
    public ResponseEntity<List<ReciboResponseDTO>> obterHistorico(
            @Parameter(description = "Período: 'semana', 'quinzena' ou 'tudo'")
            @RequestParam(defaultValue = "tudo") String periodo) {
        List<ReciboResponseDTO> historico = reciboService.listarHistorico(periodo);
        return ResponseEntity.ok(historico);
    }
    @Operation(summary = "Ver faturamento total")
    @GetMapping("/faturamento")
    public ResponseEntity<FaturamentoResponseDTO> verFaturamento() {
        return ResponseEntity.ok(reciboService.calcularFaturamento());
    }
}
