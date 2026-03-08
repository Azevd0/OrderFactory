package com.br.davyson.GerenciamentoPedidos.controllers;

import com.br.davyson.GerenciamentoPedidos.entitys.Comanda;
import com.br.davyson.GerenciamentoPedidos.enums.Periodo;
import com.br.davyson.GerenciamentoPedidos.services.ComandaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comandas")
@Tag(name = "Histórico de lançamento", description = "Consulta do histórico de itens lançados.")
public class ComandaController {
    private final ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @Operation(summary = "Listar todo o histórico de lançamentos filtrado por período.")
    @GetMapping("/itens_lancados")
    public ResponseEntity<List<Comanda>> listarHistorico(@RequestParam Periodo periodo) {
        List<Comanda> historico = comandaService.listarHistorico(periodo);
        return ResponseEntity.ok(historico);
    }
}
