package com.br.davyson.GerenciamentoPedidos.controllers;

import com.br.davyson.GerenciamentoPedidos.dto.PedidoResponseDTO;
import com.br.davyson.GerenciamentoPedidos.entitys.Pedido;
import com.br.davyson.GerenciamentoPedidos.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Gerenciamento dos pedidos do restaurante")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Listar todos os pedidos pendentes (Não pagos)")
    @GetMapping("/pendentes")
    public ResponseEntity<List<PedidoResponseDTO>> listarPendentes() {
        return ResponseEntity.ok(pedidoService.buscarPedidosPendentes());
    }

    @Operation(summary = "Buscar pedido detalhado de uma mesa específica")
    @GetMapping("/{mesa}")
    public ResponseEntity<PedidoResponseDTO> buscarPorMesa(@PathVariable Integer mesa) {
        Pedido pedido = pedidoService.buscarPorMesa(mesa);
        return ResponseEntity.ok(new PedidoResponseDTO(pedido));
    }

    @Operation(summary = "Adicionar uma comida a um pedido existente")
    @PatchMapping("/adicionar/{mesa}/{nomeComida}")
    public ResponseEntity<PedidoResponseDTO> adicionarItem(
            @PathVariable Integer mesa,
            @PathVariable String nomeComida) {
        return ResponseEntity.ok(pedidoService.adicionarComida(mesa, nomeComida));
    }

    @Operation(summary = "Transferir um item de uma mesa para outra")
    @PutMapping("/transferir/{origem}/{destino}/{comida}")
    public ResponseEntity<PedidoResponseDTO> transferir(
            @PathVariable Integer origem,
            @PathVariable Integer destino,
            @PathVariable String comida) {
        return ResponseEntity.ok(pedidoService.transferirComida(origem, destino, comida));
    }

    @Operation(summary = "Mudar número da mesa do pedido")
    @PatchMapping("/alterar-mesa/{atual}/{nova}")
    public ResponseEntity<PedidoResponseDTO> mudarMesa(
            @PathVariable Integer atual,
            @PathVariable Integer nova) {
        return ResponseEntity.ok(pedidoService.alterarMesa(atual, nova));
    }

    @Operation(summary = "Registrar pagamento")
    @PatchMapping("/pagamento/{mesa}")
    public ResponseEntity<PedidoResponseDTO> registrarPagamento(
            @PathVariable Integer mesa,
            @RequestBody BigDecimal valor) {
        return ResponseEntity.ok(pedidoService.registrarPagamento(mesa, valor));
    }
}
